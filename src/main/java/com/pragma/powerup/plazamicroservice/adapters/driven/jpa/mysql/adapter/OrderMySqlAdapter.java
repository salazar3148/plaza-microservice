package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IOrderEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Order;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.ORDER_STATUSES;

@RequiredArgsConstructor
public class OrderMySqlAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    @Override
    public Long createOrderAndGetId(Order order) {
        OrderEntity orderEntity = orderEntityMapper.orderToOrderEntity(order);
        orderRepository.save(
                orderEntity
        );
        return orderEntity.getId();
    }

    @Override
    public boolean existsByPendingStatusAndIdCustomer(Long idCustomer) {

        for (String state : ORDER_STATUSES) {
            if(orderRepository.existsByStatusAndIdCustomer(state, idCustomer)) {
                return true;
            }
        }
        return false;
    }
}
