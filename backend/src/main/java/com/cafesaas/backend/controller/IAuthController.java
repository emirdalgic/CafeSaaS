package com.cafesaas.backend.controller;

import com.cafesaas.backend.dto.AuthResponse;
import com.cafesaas.backend.dto.CafeLoginRequest;
import com.cafesaas.backend.dto.RegisterRequest;
import com.cafesaas.backend.dto.UserLoginRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    public ResponseEntity<AuthResponse> register(RegisterRequest request);
    public ResponseEntity<AuthResponse> loginUser(UserLoginRequest request);
    public ResponseEntity<AuthResponse> loginCafe(CafeLoginRequest request);
}
