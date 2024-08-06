package com.banquemisr.moneytransactionservice.repository;

import com.banquemisr.moneytransactionservice.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepsoitory extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
}
