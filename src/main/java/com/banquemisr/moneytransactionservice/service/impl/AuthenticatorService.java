package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.CreateUserDTO;
import com.banquemisr.moneytransactionservice.dto.LoginRequestDTO;
import com.banquemisr.moneytransactionservice.dto.LoginResponseDTO;
import com.banquemisr.moneytransactionservice.dto.UserDTO;
import com.banquemisr.moneytransactionservice.exception.custom.UserAlreadyExistsException;
import com.banquemisr.moneytransactionservice.model.User;
import com.banquemisr.moneytransactionservice.repository.UserRepository;
import com.banquemisr.moneytransactionservice.service.IAuthenticator;
import com.banquemisr.moneytransactionservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthenticatorService implements IAuthenticator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

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

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        return LoginResponseDTO.builder()
                .token(jwt)
                .message("Login Successful")
                .status(HttpStatus.ACCEPTED)
                .tokenType("Bearer")
                .build();
    }
}
