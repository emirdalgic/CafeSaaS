package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IUserController;
import com.cafesaas.backend.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements IUserController {
    private final IUserService userService;
}
