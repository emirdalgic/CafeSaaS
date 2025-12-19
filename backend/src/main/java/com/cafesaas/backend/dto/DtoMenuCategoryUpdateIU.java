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
public class DtoMenuCategoryUpdateIU {

    @NotBlank(message = "category name cannot be empty")
    private String name;
}
