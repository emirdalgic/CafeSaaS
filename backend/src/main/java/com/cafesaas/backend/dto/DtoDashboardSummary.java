package com.cafesaas.backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoDashboardSummary {
    private long totalOrders;
    private BigDecimal totalRevenue;
    private long totalCostumers; //şimdilik total ordersla aynı değer
}
