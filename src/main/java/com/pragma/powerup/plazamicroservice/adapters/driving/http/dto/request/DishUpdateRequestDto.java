package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishUpdateRequestDto {

    private Integer price;

    private String description;
}
