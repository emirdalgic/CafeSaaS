package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IMenuCategoryPublicController;
import com.cafesaas.backend.services.IMenuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/public/categories")
@RequiredArgsConstructor
public class MenuCategoryPublicControllerImpl implements IMenuCategoryPublicController {
    private final IMenuCategoryService menuCategoryService;
}
