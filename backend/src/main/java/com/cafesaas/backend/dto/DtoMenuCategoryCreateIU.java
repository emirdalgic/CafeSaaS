package com.cafesaas.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoMenuCategoryCreateIU {//burası ilk oluşturulmada kullanılıcak

    @NotBlank(message = "category name cannot be empty")
    private String name;

    @NotNull(message = "cafe id is required")
    private UUID cafeId;
}