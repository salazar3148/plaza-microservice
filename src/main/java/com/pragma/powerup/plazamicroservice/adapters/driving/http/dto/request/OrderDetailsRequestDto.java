package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDetailsRequestDto {
    private Long idDish;
    private Integer quantity;
}
