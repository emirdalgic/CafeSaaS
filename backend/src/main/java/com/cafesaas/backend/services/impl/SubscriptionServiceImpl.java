package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.entities.SubscriptionPayment;
import com.cafesaas.backend.entities.SubscriptionPlan;
import com.cafesaas.backend.entities.User;
import com.cafesaas.backend.entities.UserSubscription;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.model.enums.PaymentStatus;
import com.cafesaas.backend.model.enums.SubscriptionStatus;
import com.cafesaas.backend.repository.SubscriptionPaymentRepository;
import com.cafesaas.backend.repository.SubscriptionPlanRepository;
import com.cafesaas.backend.repository.UserRepository;
import com.cafesaas.backend.repository.UserSubscriptionRepository;
import com.cafesaas.backend.services.IPaymentService;
import com.cafesaas.backend.services.ISubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements ISubscriptionService {
    private final SubscriptionPaymentRepository subscriptionPaymentRepository;
    private final IPaymentService paymentService; // şimdilik mock
    private final UserRepository userRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Override
    public void purchaseSubscription(UUID userId, Long planId, String cardToken) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

        SubscriptionPlan plan = subscriptionPlanRepository.findById(planId)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

        String bankTransactionId = paymentService.processSubscriptionPayment(user, plan.getPrice(), cardToken);

        SubscriptionPayment payment = SubscriptionPayment.builder()
                .user(user)
                .plan(plan)
                .amount(plan.getPrice())
                .status(PaymentStatus.SUCCESS)
                .providerTransactionId(bankTransactionId)
                .build();
        subscriptionPaymentRepository.save(payment);


        UserSubscription userSubscription = userSubscriptionRepository.findByUser(user)
                .orElseGet(()-> {
                    return UserSubscription.builder()
                            .user(user)
                            .build();
                });

        LocalDateTime now = LocalDateTime.now();

        if(userSubscription.isActive()){
            userSubscription.setEndDate(userSubscription.getEndDate().plusDays(30));
        }
        userSubscription.setPlan(plan);
        userSubscription.setStatus(SubscriptionStatus.ACTIVE);

        userSubscriptionRepository.save(userSubscription);
    }
}
