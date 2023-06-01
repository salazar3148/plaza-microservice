package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IPlazaEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IPlazaRepository;
import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import com.pragma.powerup.plazamicroservice.domain.spi.IPlazaPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    public Page<Plaza> getPlazaList(int minPage, int sizePage) {
        Pageable pageable = PageRequest.of(minPage, sizePage);
        return plazaEntityMapper.getPlazaEntityPageToPlazaPage(plazaRepository.findAllByOrderByNameAsc(pageable));
    }

    @Override
    public boolean existById(Long id) {
        return plazaRepository.existsById(id);
    }

    @Override
    public boolean existsByNit(Long nit){
        return plazaRepository.existsByNit(nit);
    }

    @Override
    public boolean existsByIdOwnerAndId(Long idOwner, Long id) {
        return plazaRepository.existsByIdOwnerAndId(idOwner, id);
    }
}
