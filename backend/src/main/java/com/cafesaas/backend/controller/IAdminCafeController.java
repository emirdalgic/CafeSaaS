package com.cafesaas.backend.controller;

import com.cafesaas.backend.dto.DtoCafeRegisterIU;
import com.cafesaas.backend.dto.DtoCafeSummary;
import com.cafesaas.backend.dto.DtoCafeUpdateIU;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IAdminCafeController {
    public ResponseEntity<DtoCafeSummary> createCafeOnBehalfOfUser(DtoCafeRegisterIU dtoCafeRegisterIU,UUID userId);

    public ResponseEntity<DtoCafeSummary> updateCafeAsAdmin(DtoCafeUpdateIU dtoCafeUpdateIU,UUID userId, UUID cafeId);
    public ResponseEntity<Void> deleteCafe(UUID cafeId);
}
