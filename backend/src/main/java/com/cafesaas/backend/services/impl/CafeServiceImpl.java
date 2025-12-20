package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.dto.DtoCafeRegisterIU;
import com.cafesaas.backend.dto.DtoCafeSummary;
import com.cafesaas.backend.dto.DtoCafeUpdateIU;
import com.cafesaas.backend.entities.Cafe;
import com.cafesaas.backend.entities.User;
import com.cafesaas.backend.entities.UserSubscription;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.repository.CafeRepository;
import com.cafesaas.backend.repository.UserRepository;
import com.cafesaas.backend.repository.UserSubscriptionRepository;
import com.cafesaas.backend.services.ICafeService;
import com.cafesaas.backend.utils.SlugUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements ICafeService {
    private final CafeRepository cafeRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //helper
    private DtoCafeSummary mapToDto(Cafe cafe){
        return null;
    }

    @Transactional
    @Override//burada tokenden user çekmememin sebebi: ilerde support kısmında kendi panelimizden müşteriye yardım edebiliriz
    public DtoCafeSummary createCafe(DtoCafeRegisterIU dtoCafeRegisterIU, UUID userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

        UserSubscription userSubscription = userSubscriptionRepository.findByUser(owner)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

        if(!userSubscription.isActive()){
            throw new BaseException(MessageType.SUBSCRIPTION_EXPIRED);
        }

        int currentCafeCount = cafeRepository.countByOwner(owner);
        int allowedLimit = userSubscription.getCafeLimit();

        if(currentCafeCount >= allowedLimit){
            throw new BaseException(MessageType.CAFE_LIMIT_EXCEEDED);
        }

        Cafe cafe = new Cafe();
        cafe.setCafeName(dtoCafeRegisterIU.getCafeName());
        cafe.setAddress(dtoCafeRegisterIU.getAddress());
        cafe.setPhone(dtoCafeRegisterIU.getPhone());
        cafe.setPassword(passwordEncoder.encode(dtoCafeRegisterIU.getPassword()));

        cafe.setOwner(owner);

        String rawSlug = SlugUtils.toSlug(dtoCafeRegisterIU.getCafeName());
        String finalSlug = rawSlug;

        if(cafeRepository.existsBySlug(rawSlug)){
            finalSlug = rawSlug + "-" + UUID.randomUUID().toString().substring(0,4);
        }
        cafe.setSlug(finalSlug);
        cafe.setCafeUsername(finalSlug.replace("-","_"));

        Cafe savedCafe = new Cafe();
        //burada eğer başka bir thread aynı anda şans eseri aynı isimle kafe kaydı yapmaya çalışırsa küçük bir ihtimalle
        //oluşacak bir çakışma olasılığı için son bir db kontrolü ekledim
        try {
            savedCafe = cafeRepository.save(cafe);
        } catch (DataIntegrityViolationException e) {
            String newSuffix = UUID.randomUUID().toString().substring(0,4);
            String newSlug = rawSlug +"-"+ newSuffix;
            cafe.setSlug(newSlug);
            cafe.setCafeUsername(newSlug.replace("-", "_"));
            savedCafe = cafeRepository.save(cafe);
        }

        return mapToDto(savedCafe);
    }

    @Override
    public DtoCafeSummary updateCafe(DtoCafeUpdateIU dtoCafeUpdateIU, UUID userId, UUID cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));
        if(!cafe.getOwner().getId().equals(userId)){
            throw new BaseException(MessageType.UNAUTHORIZED_ACCESS);
        }

        if(dtoCafeUpdateIU.getCafeName()!= null) {
            cafe.setCafeName(dtoCafeUpdateIU.getCafeName());
        }
        if(dtoCafeUpdateIU.getPhone() != null){
            cafe.setPhone(dtoCafeUpdateIU.getPhone());
        }
        if(dtoCafeUpdateIU.getAddress()!= null){
            cafe.setAddress(dtoCafeUpdateIU.getAddress());
        }
        if(dtoCafeUpdateIU.getPassword() != null && !dtoCafeUpdateIU.getPassword().isBlank()){
            cafe.setPassword(passwordEncoder.encode(dtoCafeUpdateIU.getPassword()));
        }
        Cafe updatedCafe = cafeRepository.save(cafe);

        return mapToDto(updatedCafe);
    }

    @Override
    public void deleteCafe(UUID cafeId, UUID userId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

        if(!cafe.getOwner().getId().equals(userId)){
            throw new BaseException(MessageType.UNAUTHORIZED_ACCESS);
        }

        cafeRepository.delete(cafe);
    }
}
