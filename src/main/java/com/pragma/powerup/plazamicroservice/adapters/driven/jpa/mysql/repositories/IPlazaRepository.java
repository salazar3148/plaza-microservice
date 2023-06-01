package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.PlazaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPlazaRepository extends JpaRepository<PlazaEntity, Long> {
    Optional<PlazaEntity> findById(Long id);
    boolean existsByNit(Long nit);
    boolean existsById(Long id);
    boolean existsByIdOwnerAndId(Long idOwner, Long id);
}
