package com.banquemisr.moneytransactionservice.repository;

import com.banquemisr.moneytransactionservice.model.UserTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserTransactionsRepository extends JpaRepository<UserTransactions, Long> {
    List<UserTransactions> findByUser_CustomerId(Long userId);
}
