package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderDetailsRequestDto;
import com.pragma.powerup.plazamicroservice.domain.model.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDetailsRequestMapper {

    @Mapping(source = "idDish", target = "dish.id")
    OrderDetails orderDetailsRequestDtoToOrderDetails(OrderDetailsRequestDto orderDetailsRequestDto);
    List<OrderDetails> orderDetailsRequestDtoListToOrderDetailsList(List<OrderDetailsRequestDto> orderDetailsRequestDtoList);
}
