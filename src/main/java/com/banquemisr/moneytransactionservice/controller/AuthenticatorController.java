package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.CreateUserDTO;
import com.banquemisr.moneytransactionservice.dto.LoginRequestDTO;
import com.banquemisr.moneytransactionservice.dto.LoginResponseDTO;
import com.banquemisr.moneytransactionservice.dto.UserDTO;
import com.banquemisr.moneytransactionservice.exception.ErrorResponse;
import com.banquemisr.moneytransactionservice.exception.custom.UserAlreadyExistsException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.service.IAuthenticator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Authenticator Controller", description = "The authenticator controller endpoints")
public class AuthenticatorController {

    private final IAuthenticator userService;

    @Operation(summary = "Register the user")
    @ApiResponse(responseCode = "201", description = "User registered successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))})
    @ApiResponse(responseCode = "409", description = "User email/username already exists", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    @PostMapping("/register")
    public UserDTO register(@RequestBody CreateUserDTO createUserDTO) throws UserAlreadyExistsException {
        return userService.register(createUserDTO);
    }

    @Operation(summary = "Login user into system and generate JWT token")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = LoginResponseDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @PostMapping("/login")
    public LoginResponseDTO longin(@RequestBody LoginRequestDTO loginRequestDTO)throws UserNotFoundException {
        return userService.login(loginRequestDTO);
    }

    @Operation(summary = "Logout the user from the system")
    @ApiResponse(responseCode = "200", description = "User logged out successfully", content = {@Content(schema = @Schema(implementation = String.class))})
    @ApiResponse(responseCode = "403", description = "User not authorized", content = {@Content(examples = {})})
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        this.userService.logout(token);
        return new ResponseEntity<>(
                "User logged out successfully",
                HttpStatus.OK
        );
    }
}

