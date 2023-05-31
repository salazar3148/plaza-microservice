package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Category;

public interface ICategoryPersistencePort {
    Category getCategory(Long id);

    boolean existsById(Long id);
}
