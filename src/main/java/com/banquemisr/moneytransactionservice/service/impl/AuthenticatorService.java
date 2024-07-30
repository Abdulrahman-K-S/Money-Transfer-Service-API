package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.CreateUserDTO;
import com.banquemisr.moneytransactionservice.dto.UserDTO;
import com.banquemisr.moneytransactionservice.exception.custom.UserAlreadyExistsException;
import com.banquemisr.moneytransactionservice.model.User;
import com.banquemisr.moneytransactionservice.repository.UserRepository;
import com.banquemisr.moneytransactionservice.service.IAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthenticatorService implements IAuthenticator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void checkIfUsernameOrEmailExists(String username, String email) {
        if (Boolean.TRUE.equals(this.userRepository.existsByUsername(username))) {
            throw new UserAlreadyExistsException("This username/email already exists");
        } else if (Boolean.TRUE.equals(this.userRepository.existsByEmail(email))) {
            throw new UserAlreadyExistsException("This username/email already exists");
        }
    }

    @Override
    public User extractUserDetails(CreateUserDTO createUserDTO) {
        return User
                .builder()
                .username(createUserDTO.getUsername())
                .password(this.passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .gender(createUserDTO.getGender())
                .phone(createUserDTO.getPhone())
                .build();
    }

    @Override
    @Transactional
    public UserDTO register(CreateUserDTO createUserDTO) {
        this.checkIfUsernameOrEmailExists(createUserDTO.getUsername(), createUserDTO.getEmail());

        User user = this.extractUserDetails(createUserDTO);

        return this.userRepository.save(user).toUserDTO();
    }
}
