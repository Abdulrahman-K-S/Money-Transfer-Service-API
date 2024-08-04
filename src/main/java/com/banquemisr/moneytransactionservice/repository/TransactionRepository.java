package com.banquemisr.moneytransactionservice.repository;

import com.banquemisr.moneytransactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
