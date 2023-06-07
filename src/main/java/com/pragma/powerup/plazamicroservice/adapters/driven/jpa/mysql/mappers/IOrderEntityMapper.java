package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazamicroservice.domain.model.Order;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.CanceledState;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.DeliveredState;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.DoneState;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.InPreparationState;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.OrderStatus;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.PendingState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
    @Mapping(source = "status.toTable", target = "status")
    OrderEntity orderToOrderEntity(Order order);

    @Mapping(source = "status", target = "status", qualifiedByName = "toDomain")
    Order orderEntityToOrder(OrderEntity orderEntity);

    default Page<Order> orderEntityPageToOrderPage(Page<OrderEntity> orderEntityPage){
        return orderEntityPage.map(this::orderEntityToOrder);
    }

    @Named("toDomain")
    default OrderStatus toDomain(String status) {
        switch (status.toUpperCase()) {
            case "PENDING":
                return new PendingState();
            case "IN PREPARATION":
                return new InPreparationState();
            case "DONE":
                return new DoneState();
            case "DELIVERED":
                return new DeliveredState();
            default:
                return new CanceledState();
        }
    }
}
