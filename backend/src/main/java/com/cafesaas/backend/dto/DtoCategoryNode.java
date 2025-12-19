package com.cafesaas.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCategoryNode {
    private String categoryName;
    private UUID categoryId;
    private List<DtoMenuItem> items;
}
