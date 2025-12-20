package com.cafesaas.backend.services;

import com.cafesaas.backend.entities.User;

import java.math.BigDecimal;

public interface IPaymentService {
    public String processSubscriptionPayment(User user, BigDecimal amount, String cardToken);
}
