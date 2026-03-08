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
public class DtoOrder {
    private UUID id;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

    private List<DtoOrderItem> items;
}
