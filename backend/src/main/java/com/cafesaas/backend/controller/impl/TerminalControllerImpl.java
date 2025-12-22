package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.ITerminalController;
import com.cafesaas.backend.dto.DtoMenuItem;
import com.cafesaas.backend.dto.DtoOrderCreateIU;
import com.cafesaas.backend.entities.Cafe;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.repository.CafeRepository;
import com.cafesaas.backend.services.IMenuItemService;
import com.cafesaas.backend.services.ITerminalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("api/v1/private/terminal")
@RequiredArgsConstructor
public class TerminalControllerImpl implements ITerminalController {
    private final IMenuItemService menuItemService;
    private final CafeRepository cafeRepository;
    private final ITerminalService terminalService;


    @GetMapping("/passive")
    @Override
    public ResponseEntity<Page<DtoMenuItem>> getPassiveProducts(@AuthenticationPrincipal UserDetails userDetails,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        Cafe cafe = cafeRepository.findByCafeUsername(userDetails.getUsername())
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        return ResponseEntity.ok(menuItemService.getPassiveProducts(cafe.getId(), page, size));
    }

    @PostMapping("/order-create")
    @Override
    public ResponseEntity<Void> createOrderAtTerminal(@RequestBody @Valid DtoOrderCreateIU dtoOrderCreateIU,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        Cafe cafe = cafeRepository.findByCafeUsername(userDetails.getUsername())
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        terminalService.createOrderAtTerminal(dtoOrderCreateIU, cafe.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<Page<DtoMenuItem>> searchItems(@AuthenticationPrincipal UserDetails userDetails,
                                                         @RequestParam String query,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Cafe cafe = cafeRepository.findByCafeUsername(userDetails.getUsername())
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        return ResponseEntity.ok(menuItemService.searchItems(cafe.getId(),query,page,size));
    }

    @PatchMapping("menu-item/{menuItemId}/availability")
    @Override
    public ResponseEntity<Void> updateItemAvailability(@AuthenticationPrincipal UserDetails userDetails,
                                                          @PathVariable(name = "menuItemId") UUID menuItemId,
                                                          @RequestParam boolean isAvailable) {
        Cafe cafe = cafeRepository.findByCafeUsername(userDetails.getUsername())
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        menuItemService.updateItemAvailability(cafe.getId(),menuItemId,isAvailable);
        return ResponseEntity.ok().build();
    }
}
