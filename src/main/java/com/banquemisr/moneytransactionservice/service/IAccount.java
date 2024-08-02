package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.dto.UserTransactionDTO;
import com.banquemisr.moneytransactionservice.exception.custom.NotEnoughMoneyException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;

public interface IAccount {

    /**
     * Creates an account
     *
     * @param accountDTO The account DTO
     * @return The account created @{@link AccountDTO}
     * */
    AccountDTO createAccount(AccountDTO accountDTO);

    /**
     * Get user Account Balance
     *
     * @param id                user account number
     * @return user balance @{@link Double}
     * @throws UserNotFoundException if user not found
     */
     double getUserAccountBalance(Long id) throws UserNotFoundException ;

    /**
     * Transfer money from an account to the other
     *
     * @param transactionDTO The details of the transaction @{@link TransactionDTO}
     * @throws UserNotFoundException if user not found
     * @throws NotEnoughMoneyException if user does not have enough money
     */
     UserTransactionDTO transferMoney(TransactionDTO transactionDTO) throws UserNotFoundException , NotEnoughMoneyException ;
}
