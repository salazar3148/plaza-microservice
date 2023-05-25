package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.PersonAlreadyExistsException;
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
        if (plazaRepository.findByNit(plaza.getNit()).isPresent()) {
            throw new PersonAlreadyExistsException();
        }

        //if(userServiceAdapter.getUser())

        plazaRepository.save(plazaEntityMapper.toEntity(plaza));
    }
}
