package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.exception.custom.RefreshTokenExpiredException;
import com.banquemisr.moneytransactionservice.model.RefreshToken;

import java.util.Optional;

public interface IRefreshToken {

    /**
     * Creates a refresh token
     *
     * @param email The user's email
     * @return A refresh token object @{@link RefreshToken}
     * */
    RefreshToken createRefreshToken(String email);

    /**
     * Finds a refresh token by the token
     *
     * @param token The token to look with
     * @return A refresh token object if found @{@link RefreshToken}
     * */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Checks if a refresh token is expired
     *
     * @param token The refresh token object
     * @return The refresh token object if valid
     * @throws RefreshTokenExpiredException If the token is expired
     * */
    RefreshToken verifyExpiration(RefreshToken token) throws RefreshTokenExpiredException;
}
