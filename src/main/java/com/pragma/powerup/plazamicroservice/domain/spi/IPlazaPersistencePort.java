package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPlazaPersistencePort {
    void savePlaza(Plaza plaza);
    Plaza getPlaza(Long id);
    Page<Plaza> getPlazaList(int minPage, int sizePage);
    boolean existById(Long id);
    boolean existsByNit(Long nit);
    boolean existsByIdOwnerAndId(Long idOwner, Long id);

}
