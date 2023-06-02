package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.DishNotFoundException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Dish;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Override
    public Dish getDish(Long id) {
        return dishEntityMapper.dishEntityToDish(
                dishRepository.findById(id).orElseThrow(DishNotFoundException::new)
        );
    }

    @Override
    public Page<Dish> getDishPage(String categoryName, Integer numPage, Integer sizePage) {
        Pageable pageable = PageRequest.of(numPage, sizePage);
        return dishEntityMapper.dishPageEntityToDishPage(
            dishRepository.findAllByCategoryNameAndStatus(categoryName,true, pageable)
        );
    }
}
