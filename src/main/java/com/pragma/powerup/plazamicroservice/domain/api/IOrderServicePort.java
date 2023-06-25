package com.pragma.powerup.plazamicroservice.domain.api;

import com.pragma.powerup.plazamicroservice.domain.model.Order;
import com.pragma.powerup.plazamicroservice.domain.model.OrderDetails;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderServicePort {
    void createOrder(String token, Order order, List<OrderDetails> orderDetails);
    Page<Order> getOrdersByStatus(String token, int numPage, int sizePage, String status);
    void assignEmployeeToOrder(String token, Long orderId);
    void notifyUserOrderDone(String token, Long orderId);
}
