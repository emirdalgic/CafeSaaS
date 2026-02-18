package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.dto.RegisterRequest;
import com.cafesaas.backend.dto.UserLoginRequest;
import com.cafesaas.backend.entities.User;
import com.cafesaas.backend.model.enums.AccountStatus;
import com.cafesaas.backend.model.enums.Role;
import com.cafesaas.backend.repository.UserRepository;
import com.cafesaas.backend.security.JwtService;
import com.cafesaas.backend.services.IUserAuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cafesaas.backend.dto.AuthResponse;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements IUserAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final  RefreshTokenServiceImpl refreshTokenService;

    @Override
    public AuthResponse login(UserLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository.findByEmail(request.email()).orElseThrow();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("userId", user.getId());

        String accessToken = jwtService.generateToken(claims, user);

        String refreshToken = refreshTokenService.createRefreshToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(accessToken, refreshToken);
    }

    //ilerde mail verifikasyonu eklenicek şimdilik user işlemlerinde uğraşmayalım.
    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Bu email adresi zaten kullanımda.");
        }

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phone(request.phone())
                .role(Role.OWNER)
                .status(AccountStatus.REGISTERED)
                .build();

        userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("userId", user.getId());

        String accessToken = jwtService.generateToken(claims, user);
        String refreshToken = refreshTokenService.createRefreshToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(accessToken, refreshToken);
    }
}
