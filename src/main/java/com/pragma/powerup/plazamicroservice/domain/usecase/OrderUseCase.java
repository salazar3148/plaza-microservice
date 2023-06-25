package com.pragma.powerup.plazamicroservice.domain.usecase;
import com.pragma.powerup.plazamicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.plazamicroservice.domain.exceptions.InvalidStateTransitionException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.OrderIsNotInPendingStatusException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.PendingOrderException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.UnauthorizedException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.UnauthorizedOrderAccessException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.UnauthorizedRestaurantAccessException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.VerificationCodeMismatchException;
import com.pragma.powerup.plazamicroservice.domain.model.Order;
import com.pragma.powerup.plazamicroservice.domain.model.OrderDetails;
import com.pragma.powerup.plazamicroservice.domain.model.traceability.Traceability;
import com.pragma.powerup.plazamicroservice.domain.model.user.User;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.DoneState;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.InPreparationState;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.PendingState;
import com.pragma.powerup.plazamicroservice.domain.spi.IEmployeeRestaurantPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderDetailsPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.ISmsServicePort;
import com.pragma.powerup.plazamicroservice.domain.spi.ITraceabilityServicePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IUserServicePort;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.CUSTOMER_ROLE_ID;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.EMPLOYEE_ROLE_ID;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderDetailsPersistencePort orderDetailsPersistencePort;
    private final IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;
    private final IUserServicePort userServicePort;
    private final ISmsServicePort smsServicePort;
    private final ITraceabilityServicePort traceabilityServicePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IOrderDetailsPersistencePort orderDetailsPersistencePort, IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort, IUserServicePort userServicePort, ISmsServicePort smsServicePort, ITraceabilityServicePort traceabilityServicePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.orderDetailsPersistencePort = orderDetailsPersistencePort;
        this.employeeRestaurantPersistencePort = employeeRestaurantPersistencePort;
        this.userServicePort = userServicePort;
        this.smsServicePort = smsServicePort;
        this.traceabilityServicePort = traceabilityServicePort;
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

        generateTraceability(
                order,
                customerUser,
                new User(),
                null,
                order.getStatus().getToTable()
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

        User customerUser = userServicePort.getUserById(token, order.getIdCustomer());

        order.setIdChef(
                employeeUser.getId()
        );

        generateTraceability(
                order,
                customerUser,
                employeeUser,
                order.getStatus().getToTable(),
                order.getStatus().nextState().getToTable()
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

        generateTraceability(
                order,
                customerUser,
                employeeUser,
                order.getStatus().getToTable(),
                order.getStatus().nextState().getToTable()
        );

        order.setStatus(
                order.getStatus().nextState()
        );
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public void cancelOrder(String token, Long orderId) {
        User customerUser = userServicePort.getUser(token);

        if(!customerUser.getIdRole().equals(CUSTOMER_ROLE_ID)){
            throw new UnauthorizedException();
        }

        Order order = orderPersistencePort.getOrderById(orderId);

        if(!order.getIdCustomer().equals(customerUser.getId())){
            throw new UnauthorizedOrderAccessException();
        }

        if(!(order.getStatus() instanceof PendingState)) {
            throw new OrderIsNotInPendingStatusException();
        }

        generateTraceability(
                order,
                customerUser,
                new User(),
                order.getStatus().getToTable(),
                order.getStatus().cancel().getToTable()
        );

        order.setStatus(
                order.getStatus().cancel()
        );

        orderPersistencePort.saveOrder(order);
    }

    @Override
    public void deliverOrder(String token, Long orderId, String code) {
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

        if(!(order.getStatus() instanceof DoneState)){
            throw new InvalidStateTransitionException();
        }

        if(!order.getVerificationCode().equals(code)){
            throw new VerificationCodeMismatchException();
        }

        User customerUser = userServicePort.getUserById(token, order.getIdCustomer());

        generateTraceability(
                order,
                customerUser,
                employeeUser,
                order.getStatus().getToTable(),
                order.getStatus().nextState().getToTable()
        );

        order.setStatus(
                order.getStatus().nextState()
        );

        orderPersistencePort.saveOrder(order);
    }

    public void generateTraceability(Order order, User customerUser, User employeeUser, String oldStatus, String newStatus) {
        Traceability traceability = new Traceability(
                order.getId(),
                customerUser.getId(),
                customerUser.getMail(),
                LocalDateTime.now(),
                oldStatus,
                newStatus,
                employeeUser.getId(),
                employeeUser.getMail()
        );

        traceabilityServicePort.saveTraceability(traceability);
    }
}
