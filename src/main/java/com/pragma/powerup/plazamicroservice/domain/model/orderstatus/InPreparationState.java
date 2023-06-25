package com.pragma.powerup.plazamicroservice.domain.model.orderstatus;

public class InPreparationState implements OrderStatus{
    @Override
    public OrderStatus nextState() {
        return new DoneState();
    }
    @Override
    public String getToTable() {
        return "IN PREPARATION";
    }
}
