package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Plaza;

public interface IPlazaPersistencePort {
    void savePlaza(Plaza plaza, Long idOwner);
}
