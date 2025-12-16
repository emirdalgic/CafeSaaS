package com.cafesaas.backend.entities;

import com.cafesaas.backend.model.enums.SubscriptionPlanType;
import jakarta.persistence.*;

@Entity
@Table(name = "subscription_plans")
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_name", nullable = false)
    private SubscriptionPlanType planType; // Burada bu ürünü aboneliklere bölersek diye örn: Basic, Pro, Enterprise.
}
