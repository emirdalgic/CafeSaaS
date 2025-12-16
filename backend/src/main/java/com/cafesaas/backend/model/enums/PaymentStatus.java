package com.cafesaas.backend.model.enums;

public enum PaymentStatus {
    PENDING, SUCCESS, FAILED;

    public boolean isFinal() {
        return this == SUCCESS || this == FAILED;
    }

    public boolean isSuccessful() {
        return this == SUCCESS;
    }
}
