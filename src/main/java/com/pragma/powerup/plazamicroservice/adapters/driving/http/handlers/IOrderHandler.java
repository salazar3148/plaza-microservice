package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import org.springframework.data.domain.Page;

public interface IOrderHandler {
    void createOrder(String token, OrderRequestDto orderRequestDto);
    Page<OrderResponseDto> getOrdersByStatus(String token, int numPage, int sizePage, String status);
    void assignEmployeeToOrder(String token, Long orderId);
    void notifyUserOrderDone(String token, Long orderId);
    void cancelOrder(String token, Long orderId);
    void deliverOrder(String token, Long orderId, String code);
}
