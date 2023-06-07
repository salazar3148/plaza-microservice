package com.pragma.powerup.plazamicroservice.domain.model.orderstatus;

public class DoneState implements OrderStatus{
    @Override
    public OrderStatus nextState() {
        return new DeliveredState();
    }

    @Override
    public String getToTable() {
        return "DONE";
    }
}
