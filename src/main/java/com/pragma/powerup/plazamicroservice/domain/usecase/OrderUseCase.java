package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.plazamicroservice.domain.exceptions.PendingOrderException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.UnauthorizedException;
import com.pragma.powerup.plazamicroservice.domain.model.Order;
import com.pragma.powerup.plazamicroservice.domain.model.OrderDetails;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.PendingState;
import com.pragma.powerup.plazamicroservice.domain.model.User;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderDetailsPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IUserServicePort;
import java.time.LocalDate;
import java.util.List;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.CUSTOMER_ROLE_ID;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderDetailsPersistencePort orderDetailsPersistencePort;
    private final IUserServicePort userServicePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IOrderDetailsPersistencePort orderDetailsPersistencePort, IUserServicePort userServicePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.orderDetailsPersistencePort = orderDetailsPersistencePort;
        this.userServicePort = userServicePort;
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
}
