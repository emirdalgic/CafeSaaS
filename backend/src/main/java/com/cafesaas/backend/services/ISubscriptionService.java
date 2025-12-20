package com.cafesaas.backend.services;

import java.util.UUID;

public interface ISubscriptionService {
    public void purchaseSubscription(UUID userId, Long planId, String cardToken);
}
