package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.exception.custom.RefreshTokenExpiredException;
import com.banquemisr.moneytransactionservice.model.RefreshToken;
import com.banquemisr.moneytransactionservice.repository.RefreshTokenRepsoitory;
import com.banquemisr.moneytransactionservice.service.IRefreshToken;
import com.banquemisr.moneytransactionservice.service.IUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService implements IRefreshToken {
    private final RefreshTokenRepsoitory refreshTokenRepository;
    private final IUser userService;

    public RefreshToken createRefreshToken(String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(this.userService.getUserIfExistsByEmail(email))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(1800000))
                .build();
        return this.refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return this.refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) throws RefreshTokenExpiredException {
        if(token.getExpiryDate().compareTo(Instant.now()) < 0) {
            this.refreshTokenRepository.delete(token);
            throw new RefreshTokenExpiredException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }
}
