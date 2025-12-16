package com.cafesaas.backend.model.enums;

public enum SubscriptionStatus {
    ACTIVE, INACTIVE, SUSPENDED, EXPIRED;

    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isDeactivated() {
        return this == INACTIVE || this == SUSPENDED;
    }
}
