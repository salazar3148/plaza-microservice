package com.pragma.powerup.plazamicroservice.domain.usecase;
import com.pragma.powerup.plazamicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.plazamicroservice.domain.exceptions.OrderIsNotInPendingStatusException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.PendingOrderException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.UnauthorizedException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.UnauthorizedRestaurantAccessException;
import com.pragma.powerup.plazamicroservice.domain.model.Order;
import com.pragma.powerup.plazamicroservice.domain.model.OrderDetails;
import com.pragma.powerup.plazamicroservice.domain.model.User;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.InPreparationState;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.PendingState;
import com.pragma.powerup.plazamicroservice.domain.spi.IEmployeeRestaurantPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderDetailsPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.ISmsServicePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IUserServicePort;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.CUSTOMER_ROLE_ID;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.EMPLOYEE_ROLE_ID;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderDetailsPersistencePort orderDetailsPersistencePort;
    private final IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;
    private final IUserServicePort userServicePort;
    private final ISmsServicePort smsServicePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IOrderDetailsPersistencePort orderDetailsPersistencePort, IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort, IUserServicePort userServicePort, ISmsServicePort smsServicePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.orderDetailsPersistencePort = orderDetailsPersistencePort;
        this.employeeRestaurantPersistencePort = employeeRestaurantPersistencePort;
        this.userServicePort = userServicePort;
        this.smsServicePort = smsServicePort;
    }

    @Override
    public void createOrder(String token, Order order, List<OrderDetails> orderDetails) {

        User customerUser = userServicePort.getUser(token);

        if(!customerUser.getIdRole().equals(CUSTOMER_ROLE_ID)){
            throw new UnauthorizedException();
        }

        if(orderPersistencePort.existsByPendingStatusAndIdCustomer(customerUser.getId())){
            throw new PendingOrderException();
        }

        order.setDate(LocalDate.now());

        order.setStatus(new PendingState());

        order.setIdCustomer(
                customerUser.getId()
        );

        Long idOrder = orderPersistencePort.createOrderAndGetId(order);
        order.setId(idOrder);

        orderDetails.forEach(orderDetail -> {
                    orderDetail.setOrder(order);
                    orderDetailsPersistencePort.saveOrderDetail(orderDetail);
                }
        );
    }

    @Override
    public Page<Order> getOrdersByStatus(String token, int numPage, int sizePage, String status) {

        User employeeUser = userServicePort.getUser(token);

        if(!employeeUser.getIdRole().equals(EMPLOYEE_ROLE_ID)){
            throw new UnauthorizedException();
        }

        Long restaurantId = employeeRestaurantPersistencePort.findRestaurantIdByEmployeeId(employeeUser.getId());

        return orderPersistencePort.getOrderPageByStatus(numPage, sizePage, restaurantId, status);
    }

    @Override
    public void assignEmployeeToOrder(String token, Long orderId) {

        User employeeUser = userServicePort.getUser(token);

        if(!employeeUser.getIdRole().equals(EMPLOYEE_ROLE_ID)){
            throw new UnauthorizedException();
        }

        Order order = orderPersistencePort.getOrderById(orderId);

        if (!order.getRestaurant().getId()
                .equals(
                        employeeRestaurantPersistencePort.findRestaurantIdByEmployeeId(employeeUser.getId()
                        )
                )) {
            throw new UnauthorizedRestaurantAccessException();
        }

        if(!(order.getStatus() instanceof PendingState)){
            throw new OrderIsNotInPendingStatusException();
        }

        order.setIdChef(
                employeeUser.getId()
        );

        order.setStatus(
                order.getStatus().nextState()
        );

        orderPersistencePort.saveOrder(order);
    }

    @Override
    public void notifyUserOrderDone(String token, Long orderId) {
        User employeeUser = userServicePort.getUser(token);

        if(!employeeUser.getIdRole().equals(EMPLOYEE_ROLE_ID)){
            throw new UnauthorizedException();
        }

        Order order = orderPersistencePort.getOrderById(orderId);

        if (!order.getRestaurant().getId()
                .equals(
                        employeeRestaurantPersistencePort.findRestaurantIdByEmployeeId(employeeUser.getId()
                        )
                )) {
            throw new UnauthorizedRestaurantAccessException();
        }

        if(!(order.getStatus() instanceof InPreparationState)){
            throw new OrderIsNotInPendingStatusException();
        }

        User customerUser = userServicePort.getUserById(
            token, order.getIdCustomer()
        );

        String verificationCode = smsServicePort.sendVerificationCode(
                customerUser.getPhone()
        );

        order.setVerificationCode(verificationCode);

        order.setStatus(
                order.getStatus().nextState()
        );
        orderPersistencePort.saveOrder(order);
    }
}
