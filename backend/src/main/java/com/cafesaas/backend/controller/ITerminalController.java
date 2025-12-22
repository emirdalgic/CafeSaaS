package com.cafesaas.backend.controller;

import com.cafesaas.backend.dto.DtoMenuItem;
import com.cafesaas.backend.dto.DtoOrderCreateIU;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.print.Pageable;
import java.util.UUID;

public interface ITerminalController {
    public ResponseEntity<Page<DtoMenuItem>> getPassiveProducts(UserDetails userDetails, int page, int sizes);
    public ResponseEntity<Void> createOrderAtTerminal(DtoOrderCreateIU dtoOrderCreateIU, UserDetails userDetails);
    public ResponseEntity<Page<DtoMenuItem>> searchItems(UserDetails userDetails, String query, int page, int size);
    public ResponseEntity<Void> updateItemAvailability(UserDetails userDetails, UUID menuItemId, boolean isAvailable);
}
