package com.cafesaas.backend.entities;

import com.cafesaas.backend.model.enums.OrderStatus;
import com.cafesaas.backend.model.enums.OrderType;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    private Cafe cafe;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;


    /* burayı qr menüde ödeme alıp almama duruma göre kullanıcam
    @Enumerated(EnumType.STRING)
    private OrderType orderType; // QR_MENU veya CASH_REGISTER

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // PENDING, PAID, CANCELLED


    private String paymentTransactionId; // payment sağlayıcısından dönen id
     */
}
