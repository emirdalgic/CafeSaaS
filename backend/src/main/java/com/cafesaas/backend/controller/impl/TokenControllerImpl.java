package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.ITokenController;
import com.cafesaas.backend.dto.AuthResponse;
import com.cafesaas.backend.dto.RefreshTokenRequest;
import com.cafesaas.backend.model.redis.RefreshToken;
import com.cafesaas.backend.security.JwtService;
import com.cafesaas.backend.services.impl.RefreshTokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenControllerImpl implements ITokenController {
    private final RefreshTokenServiceImpl refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/refresh")
    @Override
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {

        RefreshToken redisToken = refreshTokenService.findByToken(request.token());

        String username = redisToken.getUsername();
        String role = redisToken.getRole();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        String newAccessToken = jwtService.generateToken(claims, username);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, request.token()));
    }
}