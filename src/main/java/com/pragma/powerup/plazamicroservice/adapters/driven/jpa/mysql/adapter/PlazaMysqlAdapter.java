package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IPlazaEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IPlazaRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import com.pragma.powerup.plazamicroservice.domain.spi.IPlazaPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlazaMysqlAdapter implements IPlazaPersistencePort {


    private final IPlazaRepository plazaRepository;
    private final IPlazaEntityMapper plazaEntityMapper;

    @Override
    public void savePlaza(Plaza plaza) {
        plazaRepository.save(plazaEntityMapper.toEntity(plaza));
    }

    @Override
    public Plaza getPlaza(Long id) {
        return plazaEntityMapper.toPlaza(
                plazaRepository.findById(id).orElseThrow()
        );
    }

    @Override
    public boolean existById(Long id) {
        return plazaRepository.existsById(id);
    }

    @Override
    public Boolean existsByNit(Long nit){
        return plazaRepository.existsByNit(nit);
    }

    @Override
    public Boolean existsByIdOwnerAndId(Long idOwner, Long id) {
        return plazaRepository.existsByIdOwnerAndId(idOwner, id);
    }
}
