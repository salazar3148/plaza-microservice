package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.PlazaRequestDto;

public interface IPlazaHandler {
    void savePlaza(PlazaRequestDto personRequestDto);
}
