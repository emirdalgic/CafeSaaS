package com.cafesaas.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DtoMenuItemIU {
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull(message = "price cannot be empty")
    @Positive(message = "item value must be higher than zero")
    private BigDecimal price;

    //@NotBlank(message = "menu item must be has a image")
    private String imageUrl;

    @NotNull(message = "please indicate the category id")
    private UUID categoryId;

    private Map<String, BigDecimal> variants;
}
