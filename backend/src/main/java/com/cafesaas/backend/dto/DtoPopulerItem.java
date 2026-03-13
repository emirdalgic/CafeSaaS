package com.cafesaas.backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoPopulerItem {
    private int rank;
    private String productName;
    private String categoryName;
    private BigDecimal price;
    private long totalSold;
}
