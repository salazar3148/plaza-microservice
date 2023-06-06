package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Order;
import org.springframework.data.domain.Page;

public interface IOrderPersistencePort {
    Long createOrderAndGetId(Order order);
    boolean existsByPendingStatusAndIdCustomer(Long idCustomer);
    Page<Order> getOrdersByStatus(int numPage, int sizePage, Long restaurantId, String status);
}
