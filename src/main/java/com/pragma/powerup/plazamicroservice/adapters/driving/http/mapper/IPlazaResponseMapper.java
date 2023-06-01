package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.PlazaResponseDto;
import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlazaResponseMapper {
    PlazaResponseDto plazaToPlazaResponseDto(Plaza plaza);
    List<PlazaResponseDto> plazaListToPlazaResponseList(List<Plaza> plazaList);
    default Page<PlazaResponseDto> plazaPageToPlazaResponseDtoPage(Page<Plaza> plazaPage) {
        return plazaPage.map(this::plazaToPlazaResponseDto);
    }
}
