package com.cafesaas.backend.services;

import com.cafesaas.backend.model.redis.RefreshToken;

public interface IRefreshTokenService {
    public String createRefreshToken(String username, String role);
    public RefreshToken findByToken(String token);
    public void deleteByToken(String token);
}
