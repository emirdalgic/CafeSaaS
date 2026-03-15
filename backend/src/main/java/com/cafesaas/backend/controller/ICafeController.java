package com.cafesaas.backend.controller;

import com.cafesaas.backend.dto.DtoCafePublic;
import com.cafesaas.backend.dto.DtoCafeRegisterIU;
import com.cafesaas.backend.dto.DtoCafeSummary;
import com.cafesaas.backend.dto.DtoCafeUpdateIU;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface ICafeController {
    public ResponseEntity<DtoCafeSummary> createCafe(DtoCafeRegisterIU dtoCafeRegisterIU, UserDetails userDetails);
    public ResponseEntity<DtoCafeSummary> updateCafe(DtoCafeUpdateIU dtoCafeUpdateIU, UserDetails userDetails, UUID cafeId);
    public ResponseEntity<Void> deleteCafe(UUID cafeId, UserDetails userDetails);
    public ResponseEntity<List<DtoCafePublic>> getMyCafes(UserDetails userDetails);
}
