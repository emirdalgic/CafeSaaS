package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IAuthController;
import com.cafesaas.backend.dto.AuthResponse;
import com.cafesaas.backend.dto.CafeLoginRequest;
import com.cafesaas.backend.dto.RegisterRequest;
import com.cafesaas.backend.dto.UserLoginRequest;
import com.cafesaas.backend.services.ICafeAuthService;
import com.cafesaas.backend.services.IUserAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements IAuthController {
    private final ICafeAuthService cafeAuthService;
    private final IUserAuthService userAuthService;


    @PostMapping(path = "/user/register")
    @Override
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(userAuthService.register(request));
    }

    @PostMapping(path = "/user/login")
    @Override
    public ResponseEntity<AuthResponse> loginUser(@RequestBody @Valid UserLoginRequest request) {
        return ResponseEntity.ok(userAuthService.login(request));
    }

    @PostMapping(path = "/cafe/login")
    @Override
    public ResponseEntity<AuthResponse> loginCafe(@RequestBody @Valid CafeLoginRequest request) {
        return ResponseEntity.ok(cafeAuthService.login(request));
    }
}
