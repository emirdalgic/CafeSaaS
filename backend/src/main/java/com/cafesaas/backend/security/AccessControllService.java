package com.cafesaas.backend.security;

import com.cafesaas.backend.entities.User;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.model.enums.SubscriptionStatus;
import com.cafesaas.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccessControllService {
    private final UserRepository userRepository;

    public void checkCafeAccess(UUID targetCafeId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

        boolean hasActiveSubsription = user.getSubscriptions().stream()
                .anyMatch(sub ->
                        sub.getStatus() == SubscriptionStatus.ACTIVE);

        if(hasActiveSubsription){
            throw new AccessDeniedException("Aktif bir aboneliğiniz bulunmamaktadır. Lütfen planınızı kontrol ediniz");
        }
        boolean isOwner = user.getCafes().stream()
                .anyMatch(cafe->
                        cafe.getId().equals(targetCafeId));

        if(!isOwner)
            throw new AccessDeniedException("Bu kafe üzerinde işlem yapma yetkiniz yok");
    }
}
