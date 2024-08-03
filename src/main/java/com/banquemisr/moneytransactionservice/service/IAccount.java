package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.dto.UserTransactionDTO;
import com.banquemisr.moneytransactionservice.exception.custom.AccountAccessNotAllowedException;
import com.banquemisr.moneytransactionservice.exception.custom.AccountNotFoundException;
import com.banquemisr.moneytransactionservice.exception.custom.NotEnoughMoneyException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;

public interface IAccount {

    /**
     * Creates an account
     *
     * @param accountDTO The account DTO
     * @param email The user's email
     * @return The account created @{@link AccountDTO}
     * */
    AccountDTO createAccount(AccountDTO accountDTO, String email);

    /**
     * Gets user Account Balance
     *
     * @param accountNumber user account number
     * @return user balance @{@link Double}
     * @throws AccountAccessNotAllowedException if user isn't the owner of the account
     * @throws AccountNotFoundException if account not found
     */
     double getUserAccountBalance(String accountNumber, String email) throws AccountNotFoundException, AccountAccessNotAllowedException;

    /**
     * Transfer money from an account to the other
     *
     * @param transactionDTO The details of the transaction @{@link TransactionDTO}
     * @throws UserNotFoundException if user not found
     * @throws NotEnoughMoneyException if user does not have enough money
     */
     UserTransactionDTO transferMoney(TransactionDTO transactionDTO) throws UserNotFoundException, NotEnoughMoneyException;
}
