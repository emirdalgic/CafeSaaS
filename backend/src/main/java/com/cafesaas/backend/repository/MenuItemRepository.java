package com.cafesaas.backend.repository;

import com.cafesaas.backend.dto.DtoMenuItem;
import com.cafesaas.backend.entities.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
    Page<MenuItem> getItemsByCategoryId(UUID categoryId, Pageable pageable);
}
