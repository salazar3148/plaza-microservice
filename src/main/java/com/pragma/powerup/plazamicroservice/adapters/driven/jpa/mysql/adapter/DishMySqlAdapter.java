package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Dish;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishMySqlAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void saveDish(Dish dish) {
        dishRepository.save(
                dishEntityMapper.dishToDishEntity(dish)
        );
    }
}
