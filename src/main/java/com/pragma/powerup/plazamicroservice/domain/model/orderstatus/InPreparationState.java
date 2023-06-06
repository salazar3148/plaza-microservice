package com.pragma.powerup.plazamicroservice.domain.model.orderstatus;

public class InPreparationState implements OrderStatus{
    @Override
    public void nextState() {

    }

    @Override
    public void prev() {

    }

    @Override
    public String getToTable() {
        return "IN PREPARATION";
    }
}
