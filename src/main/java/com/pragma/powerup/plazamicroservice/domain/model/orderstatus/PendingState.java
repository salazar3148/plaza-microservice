package com.pragma.powerup.plazamicroservice.domain.model.orderstatus;

public class PendingState implements OrderStatus{
    @Override
    public void nextState() {

    }

    @Override
    public void prev() {

    }

    @Override
    public String getToTable() {
        return "PENDING";
    }
}
