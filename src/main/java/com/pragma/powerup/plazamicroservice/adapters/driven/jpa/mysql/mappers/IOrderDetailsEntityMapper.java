package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderDetailsEntity;
import com.pragma.powerup.plazamicroservice.domain.model.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDetailsEntityMapper {

    @Mapping(source = "order.status.toTable", target = "order.status")
    OrderDetailsEntity orderDetailsToOrderDetailsEntity(OrderDetails orderDetails);

}
