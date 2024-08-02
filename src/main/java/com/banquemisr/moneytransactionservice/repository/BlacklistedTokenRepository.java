package com.banquemisr.moneytransactionservice.repository;

import com.banquemisr.moneytransactionservice.model.BlackListedTokens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlackListedTokens, String> {
    boolean existsByToken(String token);
}
