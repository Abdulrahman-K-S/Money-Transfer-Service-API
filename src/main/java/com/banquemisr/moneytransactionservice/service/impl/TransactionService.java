package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.dto.UserIdDTO;
import com.banquemisr.moneytransactionservice.dto.UserTransactionDTO;
import com.banquemisr.moneytransactionservice.model.Account;
import com.banquemisr.moneytransactionservice.model.Transaction;
import com.banquemisr.moneytransactionservice.model.User;
import com.banquemisr.moneytransactionservice.model.UserTransactions;
import com.banquemisr.moneytransactionservice.repository.TransactionRepository;
import com.banquemisr.moneytransactionservice.repository.UserTransactionsRepository;
import com.banquemisr.moneytransactionservice.service.ITransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransaction {

    private final TransactionRepository transactionRepository;
    private final UserTransactionsRepository userTransactionsRepository;

    @Override
    public Transaction createTransaction(Account fromAccount, Account toAccount, double amount) {
        return Transaction
                .builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(amount)
                .build();
    }

    @Override
    public UserTransactionDTO addToTransactionHistory(Account fromAccount, Account toAccount, double amount, User user, String status) {
        Transaction transaction = createTransaction(fromAccount, toAccount, amount);
        UserTransactions userTransaction = UserTransactions
                .builder()
                .user(user)
                .transaction(this.transactionRepository.save(transaction))
                .status(status)
                .build();
        return this.userTransactionsRepository.save(userTransaction).toDTO();
    }

    @Override
    public List<TransactionDTO> getUserTransactionHistory(UserIdDTO userId) {
        return this.userTransactionsRepository.findByUser_CustomerId(userId.getUserId())
                .stream()
                .map(userTransaction -> userTransaction.getTransaction().toDTO())
                .collect(Collectors.toList());
    }
}
