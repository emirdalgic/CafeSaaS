package com.cafesaas.backend.repository;

import com.cafesaas.backend.entities.Cafe;
import com.cafesaas.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CafeRepository extends JpaRepository<Cafe,UUID> {
    Optional<Cafe> findByCafeUsername(String cafeUsername);
    Optional<Cafe> findBySlug(String slug);
    boolean existsBySlug(String slug);
    int countByOwner(User owner);
}
