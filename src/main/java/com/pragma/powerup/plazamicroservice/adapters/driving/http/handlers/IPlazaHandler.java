package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.PlazaRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.PlazaResponseDto;
import org.springframework.data.domain.Page;

public interface IPlazaHandler {
    void savePlaza(PlazaRequestDto personRequestDto, String token);

    Page<PlazaResponseDto> getAllPlazaFilter(Integer pageNumber, Integer pageSize);
}
