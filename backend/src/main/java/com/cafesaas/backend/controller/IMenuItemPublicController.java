package com.cafesaas.backend.controller;

import com.cafesaas.backend.dto.DtoCategoryNode;
import com.cafesaas.backend.dto.DtoMenuItem;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IMenuItemPublicController {
    public ResponseEntity<Page<DtoMenuItem>> getItemsByCategoryId(UUID categoryId, int page, int size);
    public ResponseEntity<DtoMenuItem> getItemById(UUID itemId);
    public ResponseEntity<List<DtoCategoryNode>> getMenuItemsShowCase(String cafeSlug);
}
