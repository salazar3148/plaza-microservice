package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderRequestDto;

public interface IOrderHandler {
    void createOrder(String token, OrderRequestDto orderRequestDto);
}
