package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.Order;

public interface IOrderPersistencePort {
    Long createOrderAndGetId(Order order);
    boolean existsByPendingStatusAndIdCustomer(Long idCustomer);
}
