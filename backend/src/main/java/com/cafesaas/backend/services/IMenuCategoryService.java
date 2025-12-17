package com.cafesaas.backend.services;

import com.cafesaas.backend.dto.DtoMenuCategory;

import java.util.UUID;

public interface IMenuCategoryService {
    public DtoMenuCategory findCategoryById(UUID id);
}
