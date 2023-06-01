package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Dish;

public interface IDishServicePort {
    void saveDish(String token, Dish dish);
    void updateDish(String token, Long id, Dish dish);
}
