package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.PlazaEntity;
import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlazaEntityMapper {
    List<Plaza> toPlazaList(List<PlazaEntity> plazaEntityList);
    Plaza toPlaza(PlazaEntity roleEntity);
    PlazaEntity toEntity(Plaza plaza);
}
