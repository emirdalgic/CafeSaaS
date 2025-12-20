package com.cafesaas.backend.repository;

import com.cafesaas.backend.entities.User;
import com.cafesaas.backend.entities.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UUID> {
    public Optional<UserSubscription> findByUser(User user);
}
