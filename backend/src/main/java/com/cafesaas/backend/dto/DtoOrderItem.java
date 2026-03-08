package com.cafesaas.backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoOrderItem {
    private UUID menuItemId;

    private String productName; // snapshot alınan isim

    private int quantity;

    private BigDecimal priceAtPurchase; // birim fiyat

    //helper
    private BigDecimal subTotal;

    private String selectedVariant;
}
