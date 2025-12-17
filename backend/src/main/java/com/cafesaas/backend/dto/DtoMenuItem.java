package com.cafesaas.backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoMenuItem {
    private UUID id;

    private String name;

    private BigDecimal price;

    private String imageUrl;

    private boolean available;

    private UUID categoryId;
}
