package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IDishHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IDishRequestMapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IDishResponseMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHandlerImpl implements IDishHandler {
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDish(String token, DishRequestDto dishRequestDto) {
        dishServicePort.saveDish(token, dishRequestMapper.dishRequestDtoToDish(dishRequestDto));
    }

    @Override
    public void updateDish(String token, Long id,DishUpdateRequestDto dishUpdateRequestDto) {
        dishServicePort.updateDish(token, id, dishRequestMapper.disUpdateRequesDtoToDish(dishUpdateRequestDto));
    }

    @Override
    public void updateStatusDish(String token, Long id) {
        dishServicePort.updateStatusDish(token, id);
    }

    @Override
    public Page<DishResponseDto> getPageDishResponseDto(String token, String categoryName, Integer numPage, Integer sizePage) {
        return dishResponseMapper.dishPageToDishResponseDtoPage(
                dishServicePort.getPageDish(token, categoryName, numPage, sizePage)
        );
    }
}
