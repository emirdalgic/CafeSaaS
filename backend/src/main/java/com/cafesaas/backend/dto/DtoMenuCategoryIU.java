package com.cafesaas.backend.dto;

import com.cafesaas.backend.entities.Cafe;
import com.cafesaas.backend.entities.MenuItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoMenuCategoryIU{

    @Id
    private UUID id;

    private String name;

    private Cafe cafe;//buraya dönücem

    private List<MenuItem> items;
}