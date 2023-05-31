package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IDishHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IDishRequestMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHandlerImpl implements IDishHandler {
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;

    @Override
    public void saveDish(String token, DishRequestDto dishRequestDto) {
        dishServicePort.saveDish(token, dishRequestMapper.DishRequestDtoToDish(dishRequestDto));
    }
}