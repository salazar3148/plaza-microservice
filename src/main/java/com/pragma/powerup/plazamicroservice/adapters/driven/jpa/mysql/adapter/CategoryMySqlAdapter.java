package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.CategoryNotFoundException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Category;
import com.pragma.powerup.plazamicroservice.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryMySqlAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public Category getCategory(Long id) {
        return categoryEntityMapper.categoryEntityToCategory(
                categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new)
        );
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
}
