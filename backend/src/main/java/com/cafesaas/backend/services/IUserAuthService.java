package com.cafesaas.backend.services;

import com.cafesaas.backend.dto.AuthResponse;
import com.cafesaas.backend.dto.RegisterRequest;
import com.cafesaas.backend.dto.UserLoginRequest;

public interface IUserAuthService {
    public AuthResponse login(UserLoginRequest request);
    public AuthResponse register(RegisterRequest request);
}
