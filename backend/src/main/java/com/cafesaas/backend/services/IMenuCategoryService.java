package com.cafesaas.backend.services;

import com.cafesaas.backend.dto.DtoMenuCategory;
import com.cafesaas.backend.dto.DtoMenuCategoryCreateIU;
import com.cafesaas.backend.dto.DtoMenuCategoryUpdateIU;

import java.util.List;
import java.util.UUID;

public interface IMenuCategoryService {
    public void deleteCategoryById(UUID id);
    public DtoMenuCategory createCategoryMenu(DtoMenuCategoryCreateIU dtoMenuCategoryCreateIU);
    public DtoMenuCategory updateCategoryMenuNameById(UUID categoryId, DtoMenuCategoryUpdateIU dtoMenuCategoryUpdateIU);
    public List<DtoMenuCategory> getAllCategoriesByCafeId(UUID cafeId);

}
