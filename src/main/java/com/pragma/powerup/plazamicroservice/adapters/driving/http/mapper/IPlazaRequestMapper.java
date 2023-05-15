package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.PlazaRequestDto;
import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlazaRequestMapper {
    Plaza toPlaza(PlazaRequestDto personRequestDto);
}
