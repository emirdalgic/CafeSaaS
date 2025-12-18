package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.dto.DtoMenuItem;
import com.cafesaas.backend.dto.DtoMenuItemIU;
import com.cafesaas.backend.entities.Cafe;
import com.cafesaas.backend.entities.MenuCategory;
import com.cafesaas.backend.entities.MenuItem;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.repository.CafeRepository;
import com.cafesaas.backend.repository.MenuCategoryRepository;
import com.cafesaas.backend.repository.MenuItemRepository;
import com.cafesaas.backend.security.AccessControllService;
import com.cafesaas.backend.services.IMenuItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements IMenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final AccessControllService accessControllService;
    private final MenuCategoryRepository menuCategoryRepository;


    //helper fonksiyonlar
    private DtoMenuItem mapToDto(MenuItem menuItem){
        DtoMenuItem dtoMenuItem = new DtoMenuItem();
        BeanUtils.copyProperties(menuItem, dtoMenuItem);
        if(menuItem.getCategory() != null){
            dtoMenuItem.setCategoryId(menuItem.getCategory().getId());
        }
        return dtoMenuItem;
    }


    //public controllera bakan kısım
    @Override
    public Page<DtoMenuItem> getItemsByCategoryId(UUID categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MenuItem> MenuItemPage = menuItemRepository.getItemsByCategoryId(categoryId, pageable);
        return MenuItemPage.map(this::mapToDto);
    }

    @Override
    public DtoMenuItem getItemById(UUID itemId) {
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        return mapToDto(menuItem);
    }


    //private controllera bakan kısım

    @Transactional
    @Override
    public void deleteItemById(UUID itemId) {
        MenuItem menuItem = menuItemRepository.findById(itemId)
                        .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        accessControllService.checkCafeAccess(menuItem.getCategory().getCafe().getId());
        menuItemRepository.deleteById(itemId);
    }

    @Transactional
    @Override
    public DtoMenuItem createMenuItem(DtoMenuItemIU dtoMenuItemIU) {
        MenuCategory menuCategory = menuCategoryRepository.findById(dtoMenuItemIU.getCategoryId())
                        .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        accessControllService.checkCafeAccess(menuCategory.getCafe().getId());
        MenuItem menuItem = new MenuItem();
        BeanUtils.copyProperties(dtoMenuItemIU, menuItem);
        menuItem.setCategory(menuCategory);
        MenuItem savedmenuItem = menuItemRepository.save(menuItem);
        return mapToDto(savedmenuItem);
    }

    @Transactional
    @Override
    public DtoMenuItem updateMenuItemById(UUID id, DtoMenuItemIU dtoMenuItemIU) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        accessControllService.checkCafeAccess(menuItem.getCategory().getCafe().getId());
        MenuCategory menuCategory = menuCategoryRepository.findById(dtoMenuItemIU.getCategoryId())
                        .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        if(menuCategory.getId().equals(menuItem.getCategory().getId())){
            accessControllService.checkCafeAccess(menuCategory.getCafe().getId());
        }
        BeanUtils.copyProperties(dtoMenuItemIU, menuItem);
        menuItem.setCategory(menuCategory);
        MenuItem savedmenuItem = menuItemRepository.save(menuItem);
        return mapToDto(savedmenuItem);
    }

}
