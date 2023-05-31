package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Plaza;

public interface IPlazaServicePort {
    void savePlaza(Plaza plaza, String token);
}
