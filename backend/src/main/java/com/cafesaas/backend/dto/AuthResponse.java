package com.cafesaas.backend.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {}