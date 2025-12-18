package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.dto.DtoMenuCategory;
import com.cafesaas.backend.entities.MenuCategory;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.repository.MenuCategoryRepository;
import com.cafesaas.backend.services.IMenuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuCategoryServiceImpl implements IMenuCategoryService {
    private final MenuCategoryRepository menuCategoryRepository;


    //helper fonksiyonlar
    public DtoMenuCategory mapToDto(MenuCategory menuCategory){
        return null;
    }

    //public controllera bakan kısım

    @Override
    public DtoMenuCategory findCategoryById(UUID id) {
        MenuCategory menuCategory = menuCategoryRepository.findById(id)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

        return DtoMenuCategory.builder()
                .id(menuCategory.getId())
                .name(menuCategory.getName())
                .build();
    }

    //private controllera bakan kısım
}
