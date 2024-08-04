package com.banquemisr.moneytransactionservice.repository;

import com.banquemisr.moneytransactionservice.model.BlackListedTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BlacklistedTokenRepository extends JpaRepository<BlackListedTokens, String> {
    boolean existsByToken(String token);
}
