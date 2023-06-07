package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Order;
import org.springframework.data.domain.Page;

public interface IOrderPersistencePort {
    Long createOrderAndGetId(Order order);

    void saveOrder(Order order);
    boolean existsByPendingStatusAndIdCustomer(Long idCustomer);
    Page<Order> getOrderPageByStatus(int numPage, int sizePage, Long restaurantId, String status);
    Order getOrderById(Long id);

}
