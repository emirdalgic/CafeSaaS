package com.cafesaas.backend.entities;

import com.cafesaas.backend.model.enums.SubscriptionPlanType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "subscription_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_name", nullable = false)
    private SubscriptionPlanType planType; // Burada bu ürünü aboneliklere bölersek diye örn: Basic, Pro, Enterprise.

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "cafe_limit", nullable = false)
    private int cafeLimit;

    @Column(name = "description", nullable = true)
    private String description;
}
