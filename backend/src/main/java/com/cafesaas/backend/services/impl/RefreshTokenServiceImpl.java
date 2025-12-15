package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.model.redis.RefreshToken;
import com.cafesaas.backend.repository.RefreshTokenRepository;
import com.cafesaas.backend.services.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements IRefreshTokenService {
    @Value("${application.security.jwt.refresh-token.expiration}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public String createRefreshToken(String username, String role) {
        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = new RefreshToken(
                token,
                username,
                role,
                refreshTokenDurationMs
        );

        refreshTokenRepository.save(refreshToken);
        return token;
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findById(token)
                .orElseThrow(() -> new RuntimeException("Refresh Token bulunamadı veya süresi dolmuş!"));
    }

    // 3. Token Sil (Logout için)
    @Override
    public void deleteByToken(String token) {
        refreshTokenRepository.deleteById(token);
    }
}
