package com.cafesaas.backend.model.enums;

public enum SubscriptionPlanType {

    BASIC(1, 0.0),
    PRO(3, 5),
    ENTERPRISE(Integer.MAX_VALUE, 10);

    private final int maxCafeCount;
    private final double defaultPrice;

    SubscriptionPlanType(int maxCafeCount, double defaultPrice) {
        this.maxCafeCount = maxCafeCount;
        this.defaultPrice = defaultPrice;
    }

    public int getMaxCafeCount() { return maxCafeCount; }

    public double getDefaultPrice() { return defaultPrice; }
}
