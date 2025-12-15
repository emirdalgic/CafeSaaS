package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.repository.UserRepository;
import com.cafesaas.backend.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
}
