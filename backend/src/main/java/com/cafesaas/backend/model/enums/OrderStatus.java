package com.cafesaas.backend.model.enums;

public enum OrderStatus {
    WAITING_FOR_APPROVAL(false),
    APPROVED_PAYMENT_PENDING(false),
    COMPLETED(true),
    CANCELLED(true);

    private final boolean terminal;

    OrderStatus(boolean terminal) {
        this.terminal = terminal;
    }

    public boolean isActive() {
        return !terminal;
    }

    public boolean canCancel() {
        return this == WAITING_FOR_APPROVAL || this == APPROVED_PAYMENT_PENDING;
    }

    public boolean isPayable() {
        return this == APPROVED_PAYMENT_PENDING;
    }
}