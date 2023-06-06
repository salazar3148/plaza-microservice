package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderDetailsEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderDetailsId;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.DishNotFoundException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IOrderDetailsEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IOrderDetailsRepository;
import com.pragma.powerup.plazamicroservice.domain.model.OrderDetails;
import com.pragma.powerup.plazamicroservice.domain.spi.IOrderDetailsPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDetailsMySqlAdapter implements IOrderDetailsPersistencePort {

    private final IOrderDetailsRepository orderDetailsRepository;
    private final IDishRepository dishRepository;
    private final IOrderDetailsEntityMapper orderDetailsEntityMapper;

    @Override
    public void saveOrderDetail(OrderDetails orderDetails) {

        if(!dishRepository.existsById(orderDetails.getDish().getId())){
            throw new DishNotFoundException();
        }

        OrderDetailsId orderDetailsId = new OrderDetailsId(orderDetails.getOrder().getId(), orderDetails.getDish().getId());

        OrderDetailsEntity orderDetailsEntity = orderDetailsEntityMapper.orderDetailsToOrderDetailsEntity(orderDetails);
        orderDetailsEntity.setId(orderDetailsId);

        orderDetailsRepository.save(
                orderDetailsEntity
        );
    }
}
