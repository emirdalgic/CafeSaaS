package com.cafesaas.backend.services;

import com.cafesaas.backend.dto.AuthResponse;
import com.cafesaas.backend.dto.CafeLoginRequest;

public interface ICafeAuthService {
    public AuthResponse login(CafeLoginRequest request);
}
