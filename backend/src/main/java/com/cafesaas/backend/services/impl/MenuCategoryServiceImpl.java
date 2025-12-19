package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.dto.DtoMenuCategory;
import com.cafesaas.backend.dto.DtoMenuCategoryCreateIU;
import com.cafesaas.backend.dto.DtoMenuCategoryUpdateIU;
import com.cafesaas.backend.entities.Cafe;
import com.cafesaas.backend.entities.MenuCategory;
import com.cafesaas.backend.entities.MenuItem;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.repository.CafeRepository;
import com.cafesaas.backend.repository.MenuCategoryRepository;
import com.cafesaas.backend.security.AccessControllService;
import com.cafesaas.backend.services.IMenuCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuCategoryServiceImpl implements IMenuCategoryService {
    private final MenuCategoryRepository menuCategoryRepository;
    private final AccessControllService accessControllService;
    private final CafeRepository cafeRepository;


    //helper fonksiyonlar
    private DtoMenuCategory mapToDto(MenuCategory menuCategory){
        DtoMenuCategory dto = new DtoMenuCategory();
        dto.setId(menuCategory.getId());
        dto.setName(menuCategory.getName());

        if(menuCategory.getCafe() != null){
            dto.setCafeId(menuCategory.getCafe().getId());
        }
        return dto;
    }

    //public controllera bakan kısım



    //private controllera bakan kısım
    @Transactional
    @Override
    public void deleteCategoryById(UUID id) {
        MenuCategory menuCategory = menuCategoryRepository.findById(id)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        accessControllService.checkCafeAccess(menuCategory.getCafe().getId());
        if(menuCategory.getItems() != null && !menuCategory.getItems().isEmpty())
            throw new RuntimeException("Kategoriyi silmek için önce içendekileri siliniz");
        menuCategoryRepository.deleteById(menuCategory.getId());
    }

    @Transactional
    @Override
    public DtoMenuCategory createCategoryMenu(DtoMenuCategoryCreateIU dtoMenuCategoryCreateIU) {
        accessControllService.checkCafeAccess(dtoMenuCategoryCreateIU.getCafeId());
        Cafe cafe = cafeRepository.findById(dtoMenuCategoryCreateIU.getCafeId())
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setName(dtoMenuCategoryCreateIU.getName());
        menuCategory.setCafe(cafe);
        MenuCategory savedCategory = menuCategoryRepository.save(menuCategory);
        return mapToDto(savedCategory);
    }

    @Transactional
    @Override
    public DtoMenuCategory updateCategoryMenuNameById(UUID categoryId, DtoMenuCategoryUpdateIU dtoMenuCategoryUpdateIU) {
        MenuCategory menuCategory = menuCategoryRepository.findById(categoryId)
                        .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        accessControllService.checkCafeAccess(menuCategory.getCafe().getId());
        menuCategory.setName(dtoMenuCategoryUpdateIU.getName());
        MenuCategory updatedCategory = menuCategoryRepository.save(menuCategory);
        return mapToDto(updatedCategory);
    }
}
