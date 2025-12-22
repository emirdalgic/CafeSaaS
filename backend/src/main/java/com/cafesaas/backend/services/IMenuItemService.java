package com.cafesaas.backend.services;

import com.cafesaas.backend.dto.DtoCategoryNode;
import com.cafesaas.backend.dto.DtoMenuItem;
import com.cafesaas.backend.dto.DtoMenuItemIU;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface IMenuItemService {
    public Page<DtoMenuItem> getItemsByCategoryId(UUID categoryId, int page, int size);
    public DtoMenuItem getItemById(UUID itemId);
    public void deleteItemById(UUID itemId);
    public DtoMenuItem createMenuItem(DtoMenuItemIU dtoMenuItemIU);
    public DtoMenuItem updateMenuItemById(UUID id, DtoMenuItemIU dtoMenuItemIU);
    public List<DtoCategoryNode> getMenuItemsShowCase(String cafeSlug);
    public Page<DtoMenuItem> getPassiveProducts(UUID cafeId, int page, int size);
    public Page<DtoMenuItem> searchItems(UUID cafeId, String query, int page, int size);
    public void updateItemAvailability(UUID cafeId, UUID menuItemId, boolean isAvailable);
}
