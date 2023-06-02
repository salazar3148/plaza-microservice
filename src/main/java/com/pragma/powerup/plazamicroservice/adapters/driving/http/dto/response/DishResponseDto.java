package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DishResponseDto {
    String name;
    String description;
    Integer price;
    String urlImagen;
    String restaurantName;
}
