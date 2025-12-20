package com.cafesaas.backend.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IUserService {
    public UUID findIdByUsername(UserDetails userDetails);
}
