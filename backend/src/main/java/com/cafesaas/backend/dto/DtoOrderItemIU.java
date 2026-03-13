package com.cafesaas.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderItemIU {
    @NotNull
    private UUID itemId; //hangi ürün
    @Min(value = 1, message = "ürün adeti en az 1 olmalıdır")
    private int quantity;

    private String selectedVariant;
}
