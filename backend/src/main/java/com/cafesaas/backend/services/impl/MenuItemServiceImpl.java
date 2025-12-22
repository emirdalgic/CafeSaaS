package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.dto.DtoCategoryNode;
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements IMenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final AccessControllService accessControllService;
    private final MenuCategoryRepository menuCategoryRepository;
    private final CafeRepository cafeRepository;


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


    @Override
    public List<DtoCategoryNode> getMenuItemsShowCase(String cafeSlug) {
        Cafe cafe = cafeRepository.findBySlug(cafeSlug)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        List<MenuCategory> menuCategories = menuCategoryRepository.findByCafe_Id(cafe.getId());
        List<DtoCategoryNode> categoryNodeList = new ArrayList<>();
        for(MenuCategory cat : menuCategories){
            List<MenuItem> topItems = menuItemRepository.findTop5ByCategory_IdOrderByCreatedAtDesc(cat.getId());
            if(topItems.isEmpty()){
                continue;
            }
            DtoCategoryNode node = new DtoCategoryNode();
            node.setCategoryName(cat.getName());
            node.setCategoryId(cat.getId());

            node.setItems(
                    topItems.stream()
                            .map(this::mapToDto)
                            .collect(Collectors.toList()));
            categoryNodeList.add(node);
        }
        return categoryNodeList;
    }


    //private controllera bakan kısım

    @Override
    public Page<DtoMenuItem> getPassiveProducts(UUID cafeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MenuItem> menuItemPage = menuItemRepository.findAllByCategory_Cafe_IdAndAvailable(cafeId, false, pageable);
        return menuItemPage.map(this::mapToDto);
    }

    @Override
    public Page<DtoMenuItem> searchItems(UUID cafeId, String query, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<MenuItem> menuItemPage = menuItemRepository.searchActiveItems(cafeId, query, pageable);
        return menuItemPage.map(this::mapToDto);
    }

    @Override
    public void updateItemAvailability(UUID cafeId, UUID menuItemId, boolean isAvailable) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        if(!menuItem.getCategory().getCafe().getId().equals(cafeId)){
            throw new AccessDeniedException("bu ürünü düzenleme yetkiniz yok");
        }
        menuItem.setAvailable(true);
        menuItemRepository.save(menuItem);
    }

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
