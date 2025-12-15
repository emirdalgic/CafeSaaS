package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.dto.AuthResponse;
import com.cafesaas.backend.dto.CafeLoginRequest;
import com.cafesaas.backend.entities.Cafe;
import com.cafesaas.backend.repository.CafeRepository;
import com.cafesaas.backend.repository.UserRepository;
import com.cafesaas.backend.security.JwtService;
import com.cafesaas.backend.services.ICafeAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CafeAuthServiceImpl implements ICafeAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenServiceImpl refreshTokenService;
    private final CafeRepository cafeRepository;

    @Override
    public AuthResponse login(CafeLoginRequest request) {
        Cafe cafe = cafeRepository.findByCafeUsername(request.cafeUserName())
                .orElseThrow(() -> new RuntimeException("Kafe bulunamadı veya kullanıcı adı yanlış."));

        if (!passwordEncoder.matches(request.password(), cafe.getPassword())) {
            throw new RuntimeException("Hatalı şifre!");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", cafe.getRole());
        claims.put("cafeId", cafe.getId());
        claims.put("ownerId", cafe.getOwner().getId());

        String accessToken = jwtService.generateToken(claims, cafe.getCafeUsername());

        String refreshToken = refreshTokenService.createRefreshToken(cafe.getCafeUsername(), "CAFE_TERMINAL");

        return new AuthResponse(accessToken, refreshToken);
    }
}
