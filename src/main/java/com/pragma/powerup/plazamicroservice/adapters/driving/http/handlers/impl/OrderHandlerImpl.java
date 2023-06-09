package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IOrderDetailsRequestMapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IOrderRequestMapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper.IOrderResponseMapper;
import com.pragma.powerup.plazamicroservice.domain.api.IOrderServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;
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

    @Override
    public Page<OrderResponseDto> getOrdersByStatus(String token, int numPage, int sizePage, String status) {
        return orderResponseMapper.orderPageToOrderResponseDtoPage(
                orderServicePort.getOrdersByStatus(token, numPage, sizePage, status)
        );
    }

    @Override
    public void assignEmployeeToOrder(String token, Long orderId) {
        orderServicePort.assignEmployeeToOrder(token, orderId);
    }

    @Override
    public void notifyUserOrderDone(String token, Long orderId) {
        orderServicePort.notifyUserOrderDone(token, orderId);
    }

    @Override
    public void cancelOrder(String token, Long orderId) {
        orderServicePort.cancelOrder(token, orderId);
    }

    @Override
    public void deliverOrder(String token, Long orderId, String code) {
        orderServicePort.deliverOrder(token, orderId, code);
    }
}
