package com.cafesaas.backend.entities;

import com.cafesaas.backend.model.enums.PaymentMethod;
import com.cafesaas.backend.model.enums.PaymentStatus;
import jakarta.persistence.*;

import java.util.UUID;
/* burası da aynı şekilde qr menüde ödeme alma durumumuza göre şekillenicek
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method; // CREDIT_CARD, CASH, MOCK

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // PENDING, SUCCESS, FAILED

    private Double amount;

    private String providerTransactionId; // iyzico, paytr id
}*/