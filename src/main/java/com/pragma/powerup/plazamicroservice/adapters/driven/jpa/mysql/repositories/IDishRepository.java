package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findById(Long id);

    boolean existsById(Long id);

    Page<DishEntity> findAllByCategoryNameAndStatus(String categoryName, Boolean status, Pageable pageable);
}
