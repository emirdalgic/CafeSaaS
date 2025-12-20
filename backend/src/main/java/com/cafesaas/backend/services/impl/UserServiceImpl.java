package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.entities.User;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.repository.UserRepository;
import com.cafesaas.backend.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Override
    public UUID findIdByUsername(UserDetails userDetails){
        if(userDetails instanceof User){
            return ((User) userDetails).getId();
        }
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

        return user.getId();
    }
}
