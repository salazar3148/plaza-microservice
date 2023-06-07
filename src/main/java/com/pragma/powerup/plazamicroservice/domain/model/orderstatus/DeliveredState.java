package com.pragma.powerup.plazamicroservice.domain.model.orderstatus;

import com.pragma.powerup.plazamicroservice.domain.exceptions.InvalidStateTransitionException;

public class DeliveredState implements OrderStatus{
    @Override
    public OrderStatus nextState() {
        throw new InvalidStateTransitionException();
    }

    @Override
    public String getToTable() {
        return "DELIVERED";
    }
}
