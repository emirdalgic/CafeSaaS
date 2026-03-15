package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.ICafeController;
import com.cafesaas.backend.dto.DtoCafePublic;
import com.cafesaas.backend.dto.DtoCafeRegisterIU;
import com.cafesaas.backend.dto.DtoCafeSummary;
import com.cafesaas.backend.dto.DtoCafeUpdateIU;
import com.cafesaas.backend.services.ICafeService;
import com.cafesaas.backend.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/cafes")
@RequiredArgsConstructor
public class CafeControllerImpl implements ICafeController {
    private final ICafeService cafeService;
    private final IUserService userService;

    @PostMapping("/save")
    @Override
    public ResponseEntity<DtoCafeSummary> createCafe(@RequestBody @Valid DtoCafeRegisterIU dtoCafeRegisterIU,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        UUID userId = userService.findIdByUsername(userDetails);
        DtoCafeSummary response = cafeService.createCafe(dtoCafeRegisterIU, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{cafeId}")
    @Override
    public ResponseEntity<DtoCafeSummary> updateCafe(@RequestBody DtoCafeUpdateIU dtoCafeUpdateIU,
                                                     @AuthenticationPrincipal UserDetails userDetails,
                                                     @PathVariable(name = "cafeId") UUID cafeId) {
        UUID userId = userService.findIdByUsername(userDetails);
        return ResponseEntity.ok(cafeService.updateCafe(dtoCafeUpdateIU, userId, cafeId));
    }

    @DeleteMapping("/delete/{cafeId}")
    @Override
    public ResponseEntity<Void> deleteCafe(@PathVariable(name = "cafeId") UUID cafeId,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        UUID userId = userService.findIdByUsername(userDetails);
        cafeService.deleteCafe(cafeId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my-cafes")
    @Override
    public ResponseEntity<List<DtoCafePublic>> getMyCafes(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cafeService.getMyCafes(userDetails.getUsername()));
    }
}
