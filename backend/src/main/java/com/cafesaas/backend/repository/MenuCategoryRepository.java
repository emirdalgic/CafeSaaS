package com.cafesaas.backend.repository;

import com.cafesaas.backend.entities.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, UUID> {
}
