package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Plaza;

import java.util.List;

public interface IPlazaPersistencePort {
    void savePlaza(Plaza plaza);
    Plaza getPlaza(Long id);
    boolean existById(Long id);
    boolean existsByNit(Long nit);
    boolean existsByIdOwnerAndId(Long idOwner, Long id);

}
