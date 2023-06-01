package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishUpdateRequestDto;

public interface IDishHandler {
    void saveDish(String token, DishRequestDto dishRequestDto);

    void updateDish(String token, Long id, DishUpdateRequestDto dishUpdateRequestDto);

    void updateStatusDish(String token, Long id);
}
