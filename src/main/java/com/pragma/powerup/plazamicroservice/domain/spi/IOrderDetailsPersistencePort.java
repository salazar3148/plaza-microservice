package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.OrderDetails;

public interface IOrderDetailsPersistencePort {
    void saveOrderDetail(OrderDetails orderDetails);
}
