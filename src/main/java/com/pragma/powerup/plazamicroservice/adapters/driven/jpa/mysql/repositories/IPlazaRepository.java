package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.PlazaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPlazaRepository extends JpaRepository<PlazaEntity, Long> {
    Optional<PlazaEntity> findByNit(Long nit);
    Boolean existsByNit(Long nit);
}
