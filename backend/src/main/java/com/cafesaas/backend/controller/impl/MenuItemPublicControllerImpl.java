package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IMenuItemPublicController;
import com.cafesaas.backend.dto.DtoCategoryNode;
import com.cafesaas.backend.dto.DtoMenuItem;
import com.cafesaas.backend.services.IMenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/public/menu-items")
@RequiredArgsConstructor
public class MenuItemPublicControllerImpl implements IMenuItemPublicController {
    private final IMenuItemService menuItemService;


    @GetMapping(path = "/list/{categoryId}")
    @Override
    public ResponseEntity<Page<DtoMenuItem>> getItemsByCategoryId(@PathVariable(name = "categoryId") UUID categoryId,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(menuItemService.getItemsByCategoryId(categoryId,page,size));
    }

    @GetMapping(path = "/list/{itemId}")
    @Override
    public ResponseEntity<DtoMenuItem> getItemById(@PathVariable(name = "itemId") UUID itemId) {
        return ResponseEntity.ok(menuItemService.getItemById(itemId));
    }

    @GetMapping(path = "/{cafeSlug}/showcase")
    @Override
    public ResponseEntity<List<DtoCategoryNode>> getMenuItemsShowCase(@PathVariable(name = "cafeSlug") String cafeSlug) {
        return ResponseEntity.ok(menuItemService.getMenuItemsShowCase(cafeSlug));
    }
}
