package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IOrderDetailsRequestMapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IOrderServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderHandlerImpl implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderDetailsRequestMapper orderDetailsRequestMapper;


    @Transactional
    @Override
    public void createOrder(String token, OrderRequestDto orderRequestDto) {
        orderServicePort.createOrder(
                token,
                orderRequestMapper.orderRequestDtoToOrder(orderRequestDto),
                orderDetailsRequestMapper.orderDetailsRequestDtoListToOrderDetailsList(orderRequestDto.getOrderDetailsRequestDtoList()
                ));
    }
}
