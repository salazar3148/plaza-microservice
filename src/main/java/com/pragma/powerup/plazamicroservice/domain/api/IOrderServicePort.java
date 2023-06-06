package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Order;
import com.pragma.powerup.plazamicroservice.domain.model.OrderDetails;

import java.util.List;

public interface IOrderServicePort {
    void createOrder(String token, Order order, List<OrderDetails> orderDetails);
}
