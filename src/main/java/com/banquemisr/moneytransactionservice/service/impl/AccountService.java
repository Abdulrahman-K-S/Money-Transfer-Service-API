package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.dto.UserTransactionDTO;
import com.banquemisr.moneytransactionservice.exception.custom.NotEnoughMoneyException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.model.Account;
import com.banquemisr.moneytransactionservice.model.Transaction;
import com.banquemisr.moneytransactionservice.repository.AccountRepository;
import com.banquemisr.moneytransactionservice.service.IAccount;
import com.banquemisr.moneytransactionservice.service.ITransaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService implements IAccount {
    private final AccountRepository accountRepository;
    private final ITransaction transactionService;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = Account
                .builder()
                .accountName(accountDTO.getAccountName())
                .accountDescription(accountDTO.getAccountDescription())
                .accountNumber(accountDTO.getAccountNumber())
                .accountType(accountDTO.getAccountType())
                .accountCurrency(accountDTO.getAccountCurrency())
                .isActive(accountDTO.getIsActive())
                .balance(accountDTO.getBalance())
                .build();
        return this.accountRepository.save(account).ToDTO();
    }

    @Override
    public double getUserAccountBalance(Long accountNumber) throws UserNotFoundException {
        Account account = this.accountRepository.findById(accountNumber).orElseThrow(UserNotFoundException::new);
        return account.getBalance();
    }

    @Override
    public UserTransactionDTO transferMoney(TransactionDTO transactionDTO) throws UserNotFoundException , NotEnoughMoneyException {
        Account fromAccount = this.accountRepository.findByAccountNumber(transactionDTO.getFromAccountNumber()).orElseThrow(UserNotFoundException::new);
        Account toAccount = this.accountRepository.findByAccountNumber(transactionDTO.getToAccountNumber()).orElseThrow(UserNotFoundException::new);

        if (fromAccount.getBalance() < transactionDTO.getAmount()) {
            throw new NotEnoughMoneyException();
        }
        fromAccount.setBalance(fromAccount.getBalance() - transactionDTO.getAmount());
        toAccount.setBalance(toAccount.getBalance() + transactionDTO.getAmount());
        this.accountRepository.save(fromAccount);
        this.accountRepository.save(toAccount);

        Transaction transaction = this.transactionService.createTransaction(fromAccount, toAccount, transactionDTO.getAmount());
        return this.transactionService.addToTransactionHistory(transaction, fromAccount.getUser());
    }
}
