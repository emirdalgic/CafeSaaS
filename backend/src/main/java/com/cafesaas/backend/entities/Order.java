package com.cafesaas.backend.entities;

import com.cafesaas.backend.model.enums.OrderStatus;
import com.cafesaas.backend.model.enums.OrderSource;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    private Cafe cafe;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;


    @Column(name = "table_name")
    private String tableName;



    //belki ilerde paranın cash mi postan mı yapıldığını tutarız
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderSource source = OrderSource.TERMINAL; // QR_MENU veya Terminal

    /*
    WAITING_FOR_APPROVAL(false),
    APPROVED_PAYMENT_PENDING(false),
    PREPARING(false),
    READY(false),
    COMPLETED(true),
    CANCELLED(true);*/
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    private String paymentTransactionId; // payment sağlayıcısından dönen id
}
