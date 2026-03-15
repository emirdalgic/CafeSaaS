package com.cafesaas.backend.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoOrderHistory {
    private UUID orderId;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private List<DtoOrderHistoryItem> items;
}