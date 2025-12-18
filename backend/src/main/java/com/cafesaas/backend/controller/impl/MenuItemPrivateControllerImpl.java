package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IMenuItemPrivateController;
import com.cafesaas.backend.dto.DtoMenuItem;
import com.cafesaas.backend.dto.DtoMenuItemIU;
import com.cafesaas.backend.services.IMenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/menu-items")
@RequiredArgsConstructor
public class MenuItemPrivateControllerImpl implements IMenuItemPrivateController {
    private final IMenuItemService menuItemService;

    @DeleteMapping(path = "/delete/{itemId}")
    @Override
    public ResponseEntity<Void> deleteItemById(@PathVariable(name = "itemId") UUID itemId) {
        menuItemService.deleteItemById(itemId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    @Override
    public ResponseEntity<DtoMenuItem> createMenuItem(@RequestBody DtoMenuItemIU dtoMenuItemIU) {
        DtoMenuItem response = menuItemService.createMenuItem(dtoMenuItemIU);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<DtoMenuItem> updateMenuItemById(@PathVariable(name = "id") UUID id,
                                                          @RequestBody DtoMenuItemIU dtoMenuItemIU) {
        return ResponseEntity.ok(menuItemService.updateMenuItemById(id,dtoMenuItemIU));
    }
}
