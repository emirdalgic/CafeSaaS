package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IMenuCategoryPrivateController;
import com.cafesaas.backend.services.IMenuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/private/categories")
@RequiredArgsConstructor
public class MenuCategoryPrivateControllerImpl implements IMenuCategoryPrivateController {
    private final IMenuCategoryService menuCategoryService;
}
