package com.cafesaas.backend.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoOrderHistoryItem {
    private String productName;
    private String selectedVariant;
    private Integer quantity;
    private BigDecimal price;
}