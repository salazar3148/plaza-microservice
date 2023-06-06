package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrderRequestDto {

    @NotNull
    private Long idRestaurant;

    @NotEmpty
    private List<OrderDetailsRequestDto> orderDetailsRequestDtoList;
}
