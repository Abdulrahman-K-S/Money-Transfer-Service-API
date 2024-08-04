package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.dto.UserTransactionDTO;
import com.banquemisr.moneytransactionservice.exception.custom.*;
import com.banquemisr.moneytransactionservice.model.Accounts;

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
      * Checks whether the fromAccount or toAccount are expired
      *
      * @param fromAccount The account the transfer is being deducted from
      * @param toAccount The account the transfer is being added to
      * @throws AccountExpiredException If either accounts are expired
      * */
     void checkIfFromAndToAccountAreExpired(Accounts fromAccount, Accounts toAccount) throws AccountExpiredException;

     /**
      * Checks whether the fromAccount or toAccount are active
      *
      * @param fromAccount The account the transfer is being deducted from
      * @param toAccount The account the transfer is being added to
      * @throws AccountNotActiveException If either accounts are not active
      * */
     void checkIfFromAndToAccountAreActive(Accounts fromAccount, Accounts toAccount) throws AccountNotActiveException;

     /**
      * Checks whether the fromAccount has enough money for transfer, and adds
      * a failed transaction to transaction history if there's not enough money
      *
      * @param fromAccount The account the transfer is being deducted from
      * @param toAccount The account the transfer is being added to
      * @param amount The amount being transferred
      * @throws NotEnoughMoneyInAccountException If there's not enough money in the fromAccount
      * */
     void checkIfFromAccountHasEnoughMoney(Accounts fromAccount, Accounts toAccount, double amount) throws NotEnoughMoneyInAccountException;

    /**
     * Performs the transaction and adds a successful transaction to transaction history
     *
     * @param fromAccount The account the transfer is being deducted from
     * @param toAccount The account the transfer is being added to
     * @param amount The amount being transferred
     * */
     UserTransactionDTO performTransferTransaction(Accounts fromAccount, Accounts toAccount, double amount);

    /**
     * Transfer money from an account to the other
     *
     * @param transactionDTO The details of the transaction @{@link TransactionDTO}
     * @throws UserNotFoundException if user not found
     * @throws NotEnoughMoneyInAccountException if user does not have enough money
     */
     UserTransactionDTO transferMoney(TransactionDTO transactionDTO) throws UserNotFoundException, NotEnoughMoneyInAccountException;
}
