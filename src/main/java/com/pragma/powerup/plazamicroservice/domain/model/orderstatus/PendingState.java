package com.pragma.powerup.plazamicroservice.domain.model.orderstatus;

public class PendingState implements OrderStatus{
    @Override
    public OrderStatus nextState() {
        return new InPreparationState();
    }

    @Override
    public OrderStatus cancel() {
        return new CanceledState();
    }

    @Override
    public String getToTable() {
        return "PENDING";
    }
}
