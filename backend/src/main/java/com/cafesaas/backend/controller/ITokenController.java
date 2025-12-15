package com.cafesaas.backend.controller;

import com.cafesaas.backend.dto.AuthResponse;
import com.cafesaas.backend.dto.RefreshTokenRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ITokenController {
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request);
}
