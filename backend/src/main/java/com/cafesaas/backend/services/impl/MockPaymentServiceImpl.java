package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.entities.User;
import com.cafesaas.backend.services.IPaymentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class MockPaymentServiceImpl implements IPaymentService {

    @Override
    public String processSubscriptionPayment(User user, BigDecimal amount, String cardToken) {
        System.out.println("banka similasyonu: "+ user.getEmail()+" hesabınızdan "+amount+" tl çekildi");
        return UUID.randomUUID().toString();
    }
}
