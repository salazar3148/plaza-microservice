package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Dish;
import org.springframework.data.domain.Page;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    Dish getDish(Long id);
    Page<Dish> getDishPage(String category, Integer numPage, Integer sizePage);
}
