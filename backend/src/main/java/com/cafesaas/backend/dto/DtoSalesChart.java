package com.cafesaas.backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoSalesChart {
    private String period;
    private BigDecimal revenue;
    private long orderCount;
}
