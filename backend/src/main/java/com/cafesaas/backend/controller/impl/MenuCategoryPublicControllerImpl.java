package com.cafesaas.backend.controller.impl;

import com.cafesaas.backend.controller.IMenuCategoryPublicController;
import com.cafesaas.backend.dto.DtoMenuCategory;
import com.cafesaas.backend.services.IMenuCategoryService;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/api/public/categories")
@RequiredArgsConstructor
public class MenuCategoryPublicControllerImpl implements IMenuCategoryPublicController {
    private final IMenuCategoryService menuCategoryService;


}
