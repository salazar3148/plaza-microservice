package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.PlazaRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.PlazaResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IPlazaHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IPlazaRequestMapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IPlazaResponseMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IPlazaServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlazaHandlerImpl implements IPlazaHandler {
    private final IPlazaServicePort plazaServicePort;
    private final IPlazaRequestMapper plazaRequestMapper;
    private final IPlazaResponseMapper plazaResponseMapper;

    @Override
    public void savePlaza(PlazaRequestDto plazaRequestDto, String token) {
        plazaServicePort.savePlaza(plazaRequestMapper.toPlaza(plazaRequestDto), token);
    }

    @Override
    public Page<PlazaResponseDto> getAllPlazaFilter(String token, Integer pageNumber, Integer pageSize) {
        return plazaResponseMapper.plazaPageToPlazaResponseDtoPage(plazaServicePort.getAllPlazaFilter(token, pageNumber, pageSize));
    }
}
