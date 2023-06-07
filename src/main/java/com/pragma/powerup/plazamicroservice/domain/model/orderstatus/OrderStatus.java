package com.pragma.powerup.plazamicroservice.domain.model.orderstatus;

public interface OrderStatus {
    OrderStatus nextState();
    String getToTable();
}
