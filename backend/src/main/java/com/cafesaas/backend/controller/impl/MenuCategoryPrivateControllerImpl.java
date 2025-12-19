package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IMenuCategoryPrivateController;
import com.cafesaas.backend.dto.DtoMenuCategory;
import com.cafesaas.backend.dto.DtoMenuCategoryCreateIU;
import com.cafesaas.backend.dto.DtoMenuCategoryUpdateIU;
import com.cafesaas.backend.services.IMenuCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/categories")
@RequiredArgsConstructor
public class MenuCategoryPrivateControllerImpl implements IMenuCategoryPrivateController {
    private final IMenuCategoryService menuCategoryService;

    @DeleteMapping("/delete/{categoryId}")
    @Override
    public ResponseEntity<Void> deleteCategoryById(@PathVariable(name = "categoryId") UUID categoryId) {
        menuCategoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    @Override
    public ResponseEntity<DtoMenuCategory> createCategoryMenu(@RequestBody @Valid DtoMenuCategoryCreateIU dtoMenuCategoryCreateIU) {
        DtoMenuCategory response = menuCategoryService.createCategoryMenu(dtoMenuCategoryCreateIU);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{categoryId}")
    @Override
    public ResponseEntity<DtoMenuCategory> updateCategoryMenuNameById(@PathVariable(name = "categoryId") UUID categoryId,
                                                                  @RequestBody @Valid DtoMenuCategoryUpdateIU dtoMenuCategoryUpdateIU) {
        return ResponseEntity.ok(menuCategoryService.updateCategoryMenuNameById(categoryId, dtoMenuCategoryUpdateIU));
    }
}
