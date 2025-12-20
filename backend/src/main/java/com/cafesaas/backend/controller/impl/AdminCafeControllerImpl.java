package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IAdminCafeController;
import com.cafesaas.backend.dto.DtoCafeRegisterIU;
import com.cafesaas.backend.dto.DtoCafeSummary;
import com.cafesaas.backend.dto.DtoCafeUpdateIU;
import com.cafesaas.backend.entities.Cafe;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.repository.CafeRepository;
import com.cafesaas.backend.services.ICafeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin/cafes")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminCafeControllerImpl implements IAdminCafeController {
    private final ICafeService cafeService;
    private final CafeRepository cafeRepository;


    @PostMapping("/user/{userId}")
    @Override
    public ResponseEntity<DtoCafeSummary> createCafeOnBehalfOfUser(@RequestBody @Valid DtoCafeRegisterIU dtoCafeRegisterIU,
                                                                   @PathVariable(name = "userIds") UUID userId) {
        DtoCafeSummary response = cafeService.createCafe(dtoCafeRegisterIU, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{cafeId}")
    @Override
    public ResponseEntity<DtoCafeSummary> updateCafeAsAdmin(@RequestBody DtoCafeUpdateIU dtoCafeUpdateIU, UUID userId, UUID cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        DtoCafeSummary response = cafeService.updateCafe(dtoCafeUpdateIU,userId,cafe.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("force-delete/{cafeId}")
    @Override
    public ResponseEntity<Void> deleteCafe(@PathVariable(name = "cafeId") UUID cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                        .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

        cafeService.deleteCafe(cafe.getId(), cafe.getOwner().getId());
        return ResponseEntity.noContent().build();
    }
}
