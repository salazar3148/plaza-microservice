package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Dish;
import org.springframework.data.domain.Page;

public interface IDishServicePort {
    void saveDish(String token, Dish dish);
    void updateDish(String token, Long id, Dish dish);
    void updateStatusDish(String token, Long id);
    Page<Dish> getPageDish(String token, String catogryName, Integer minPage, Integer sizePage);
}
