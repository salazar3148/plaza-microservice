package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazamicroservice.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {

    @Mapping(source = "status.toTable", target = "status")
    OrderResponseDto ordertoToOrderResponseDto(Order order);

    default Page<OrderResponseDto> orderPageToOrderResponseDtoPage(Page<Order> orderPage) {
        return orderPage.map(this::ordertoToOrderResponseDto);
    }
}
