package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.PlazaRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IPlazaHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IPlazaRequestMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IPlazaServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlazaHandlerImpl implements IPlazaHandler {
    private final IPlazaServicePort plazaServicePort;
    private final IPlazaRequestMapper plazaRequestMapper;

    @Override
    public void savePlaza(PlazaRequestDto plazaRequestDto, String token) {
        plazaServicePort.savePlaza(plazaRequestMapper.toPlaza(plazaRequestDto), token);
    }
}
