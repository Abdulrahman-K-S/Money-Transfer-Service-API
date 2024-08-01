package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.exception.custom.NoTransactionsMadeException;
import com.banquemisr.moneytransactionservice.exception.custom.NotEnoughMoneyException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;

import java.util.HashMap;

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
     * Transfer money to another user
     *
     * @param fromAccountNumber               sender user account number
     * @param toAccountNumber               receiver user account number
     * @param balance                      the amount of the transaction
     * @throws UserNotFoundException if user not found
     * @throws NotEnoughMoneyException if user does not have enough money
     */
     void transferMoney(Long fromAccountNumber,Long toAccountNumber,double balance) throws UserNotFoundException , NotEnoughMoneyException ;

    /**
     * Get user transaction history
     *
     * @param accountNumber                user account number
     * @return transaction history @{@link HashMap<Long,Double>}
     * @throws UserNotFoundException if user not found
     * @throws NoTransactionsMadeException if user has not made any transactions
     */
     HashMap<Long, Double> transactionHistory(Long accountNumber) throws UserNotFoundException,NoTransactionsMadeException;


}
