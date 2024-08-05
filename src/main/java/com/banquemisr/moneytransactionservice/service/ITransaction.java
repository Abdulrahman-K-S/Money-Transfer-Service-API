package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.dto.UserIdDTO;
import com.banquemisr.moneytransactionservice.dto.UserTransactionDTO;
import com.banquemisr.moneytransactionservice.model.Account;
import com.banquemisr.moneytransactionservice.model.Transaction;
import com.banquemisr.moneytransactionservice.model.User;

import java.util.List;

public interface ITransaction {

    /**
     * Creates the transaction object from the transaction done by the user
     *
     * @param fromAccount The account the money is being transferred from
     * @param toAccount The account the money is being transferred to
     * @param amount The amount being transferred
     * @return The transaction object @{@link Transaction}
     * */
    Transaction createTransaction(Account fromAccount, Account toAccount, double amount);

    /**
     * Add a transaction to the user's transaction history
     *
     * @param fromAccount  The account the money is being transferred from
     * @param toAccount    The account the money is being transferred to
     * @param amount       The amount being transferred
     * @param user         The user doing the transaction
     * @param status       The status of the transaction
     * @return user transaction table details @{@link UserTransactionDTO}
     * */
    UserTransactionDTO addToTransactionHistory(Account fromAccount, Account toAccount, double amount, User user, String status);

    /**
     * Retrieves the user transaction history
     *
     * @param email The user's email
     * @return List of the transactions done by the user @{@link TransactionDTO}
     * */
    List<TransactionDTO> getUserTransactionHistory(String email);
}
