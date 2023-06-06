package com.pragma.powerup.plazamicroservice.domain.model.orderstatus;

public interface OrderStatus {
    void nextState();
    void prev();
    String getToTable();
}
