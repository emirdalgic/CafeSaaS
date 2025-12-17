package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IMenuCategoryController;
import com.cafesaas.backend.services.IMenuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/categories")
@RequiredArgsConstructor
public class MenuCategoryImpl implements IMenuCategoryController {
    private final IMenuCategoryService menuCategoryService;
}
