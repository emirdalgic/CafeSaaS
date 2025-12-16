package com.cafesaas.backend.model.enums;

public enum OrderStatus {
    PENDING, PAID, CANCELLED;

    public boolean canTransitionTo(OrderStatus next) {
        if (this == CANCELLED) return false;
        if (this == PAID && next == PENDING) return false;
        return true;
    }
}
