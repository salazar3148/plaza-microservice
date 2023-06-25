package com.pragma.powerup.plazamicroservice.domain.model.orderstatus;

import com.pragma.powerup.plazamicroservice.domain.exceptions.OrderIsNotInPendingStatusException;

public interface OrderStatus {
    OrderStatus nextState();
    default OrderStatus cancel() {
        throw new OrderIsNotInPendingStatusException();
    }
    String getToTable();
}
