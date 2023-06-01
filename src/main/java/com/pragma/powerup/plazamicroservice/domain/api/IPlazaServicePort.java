package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import org.springframework.data.domain.Page;

public interface IPlazaServicePort {
    void savePlaza(Plaza plaza, String token);

    Page<Plaza> getAllPlazaFilter(String token, int minPage, int sizePage);
}
