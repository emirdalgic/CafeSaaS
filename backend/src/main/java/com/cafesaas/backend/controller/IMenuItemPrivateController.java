package com.cafesaas.backend.controller;

import com.cafesaas.backend.dto.DtoMenuItem;
import com.cafesaas.backend.dto.DtoMenuItemIU;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IMenuItemPrivateController {
    public ResponseEntity<Void> deleteItemById(UUID itemId);
    public ResponseEntity<DtoMenuItem> createMenuItem(DtoMenuItemIU dtoMenuItemIU);
    public ResponseEntity<DtoMenuItem> updateMenuItemById(UUID id,DtoMenuItemIU dtoMenuItemIU);
}
