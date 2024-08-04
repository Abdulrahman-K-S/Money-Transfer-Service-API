package com.banquemisr.moneytransactionservice.repository;

import com.banquemisr.moneytransactionservice.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByAccountNumber(String accountNumber);

    Boolean existsByAccountNumber(String accountNumber);
    Boolean existsAccountByUser_EmailAndAccountNumber(String email, String accountNumber);
}
