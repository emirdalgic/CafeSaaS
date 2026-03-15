package com.cafesaas.backend.controller;

import com.cafesaas.backend.dto.DtoMenuCategory;
import com.cafesaas.backend.dto.DtoMenuCategoryCreateIU;
import com.cafesaas.backend.dto.DtoMenuCategoryUpdateIU;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IMenuCategoryPrivateController {
    public ResponseEntity<Void> deleteCategoryById(UUID categoryId);
    public ResponseEntity<DtoMenuCategory> createCategoryMenu(DtoMenuCategoryCreateIU dtoMenuCategoryCreateIU);
    public ResponseEntity<DtoMenuCategory> updateCategoryMenuNameById(UUID categoryId, DtoMenuCategoryUpdateIU dtoMenuCategoryUpdateIU);
    public ResponseEntity<List<DtoMenuCategory>> getAllCategoriesByCafeId(UUID cafeId);
}
