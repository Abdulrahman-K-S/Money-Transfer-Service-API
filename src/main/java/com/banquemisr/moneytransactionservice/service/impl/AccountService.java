package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.dto.UserTransactionDTO;
import com.banquemisr.moneytransactionservice.exception.custom.*;
import com.banquemisr.moneytransactionservice.model.Account;
import com.banquemisr.moneytransactionservice.model.Transaction;
import com.banquemisr.moneytransactionservice.repository.AccountRepository;
import com.banquemisr.moneytransactionservice.service.IAccount;
import com.banquemisr.moneytransactionservice.service.ITransaction;
import com.banquemisr.moneytransactionservice.service.IUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class AccountService implements IAccount {
    private final AccountRepository accountRepository;
    private final ITransaction transactionService;
    private final IUser userService;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO, String email) {
        if (Boolean.TRUE.equals(this.accountRepository.existsByAccountNumber(accountDTO.getAccountNumber()))) {
            throw new AccountAlreadyExistsException(String.format("Account number %s already exists", accountDTO.getAccountNumber()));
        }
        Account account = Account
                .builder()
                .accountName(accountDTO.getAccountName())
                .accountNumber(accountDTO.getAccountNumber())
                .expiryMonth(accountDTO.getExpiryDate().substring(0, 2))
                .expiryYear(accountDTO.getExpiryDate().substring(3, 5))
                .accountCurrency(accountDTO.getAccountCurrency())
                .cvv(accountDTO.getCvv())
                .isActive(accountDTO.getIsActive())
                .balance(accountDTO.getBalance())
                .otp(String.format("%06d", new SecureRandom().nextInt(100000)))
                .user(userService.getUserIfExistsByEmail(email))
                .build();
        return this.accountRepository.save(account).ToDTO();
    }

    @Override
    public double getUserAccountBalance(String accountNumber, String email) throws AccountNotFoundException, AccountAccessNotAllowedException {
        if (Boolean.FALSE.equals(accountRepository.existsAccountByUser_EmailAndAccountNumber(email, accountNumber))) {
            throw new AccountAccessNotAllowedException(String.format("User with email %s isn't the account owner", email));
        }
        Account account = this.accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account with account number %s not found", accountNumber)));
        return account.getBalance();
    }

    @Override
    public UserTransactionDTO transferMoney(TransactionDTO transactionDTO) throws UserNotFoundException , NotEnoughMoneyException {
        Account fromAccount = this.accountRepository.findByAccountNumber(transactionDTO.getFromAccountNumber()).orElseThrow(UserNotFoundException::new);
        Account toAccount = this.accountRepository.findByAccountNumber(transactionDTO.getToAccountNumber()).orElseThrow(UserNotFoundException::new);

        // Check if the card transferred from or to is expired
        // Check if the card transferred from or to is active
        if (fromAccount.getBalance() < transactionDTO.getAmount()) {
            // Save failed transaction for web/application view
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
