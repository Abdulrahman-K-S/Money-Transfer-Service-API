package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.model.Users;
import com.banquemisr.moneytransactionservice.repository.UserRepository;
import com.banquemisr.moneytransactionservice.service.IUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUser {

    private final UserRepository userRepository;


    @Override
    public Users getUserIfExistsById(Long userId) {
        return this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with user id %d", userId)));
    }

    @Override
    public Users getUserIfExistsByEmail(String email) {
        return this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with user email %s not found", email)));
    }
}
