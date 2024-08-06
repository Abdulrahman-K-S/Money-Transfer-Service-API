package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.dto.UserTransactionDTO;
import com.banquemisr.moneytransactionservice.exception.custom.*;
import com.banquemisr.moneytransactionservice.model.Account;
import com.banquemisr.moneytransactionservice.repository.AccountRepository;
import com.banquemisr.moneytransactionservice.service.IAccount;
import com.banquemisr.moneytransactionservice.service.ITransaction;
import com.banquemisr.moneytransactionservice.service.IUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.YearMonth;

@Service
@AllArgsConstructor
public class AccountService implements IAccount {
    private final AccountRepository accountRepository;
    private final ITransaction transactionService;
    private final IUser userService;

    @Override
    @Transactional
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
    @Transactional
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
    public void checkIfFromAndToAccountAreExpired(Account fromAccount, Account toAccount) throws AccountExpiredException {
        YearMonth currentYearMonth = YearMonth.now();

        YearMonth fromAccountYearMonth = YearMonth.of(Integer.parseInt("20" + fromAccount.getExpiryYear()), Integer.parseInt(fromAccount.getExpiryMonth()));
        if (fromAccountYearMonth.isBefore(currentYearMonth)) {
            throw new AccountExpiredException(String.format("Account %s is expired", fromAccount.getAccountNumber()));
        }

        YearMonth toAccountYearMonth = YearMonth.of(Integer.parseInt("20" + toAccount.getExpiryYear()), Integer.parseInt(toAccount.getExpiryMonth()));
        if (toAccountYearMonth.isBefore(currentYearMonth)) {
            throw new AccountExpiredException(String.format("Account %s is expired", toAccount.getAccountNumber()));
        }
    }

    @Override
    public void checkIfFromAndToAccountAreActive(Account fromAccount, Account toAccount) throws AccountExpiredException {
        if (Boolean.FALSE.equals(fromAccount.getIsActive())) {
            throw new AccountNotActiveException(String.format("Account %s is not active ", fromAccount.getAccountNumber()));
        }
        if (Boolean.FALSE.equals(toAccount.getIsActive())) {
            throw new AccountNotActiveException(String.format("Account %s is not active ", toAccount.getAccountNumber()));
        }
    }

    @Override
    public void checkIfFromAccountHasEnoughMoney(Account fromAccount, Account toAccount, double amount) throws NotEnoughMoneyInAccountException {
        if (fromAccount.getBalance() < amount) {
            // Save failed transaction for web/application view
            this.transactionService.addToTransactionHistory(fromAccount, toAccount, amount, fromAccount.getUser(), "fail");
            throw new NotEnoughMoneyInAccountException(String.format("Account number %s doesn't have enough money", fromAccount.getAccountNumber()));
        }
    }

    @Override
    public UserTransactionDTO performTransferTransaction(Account fromAccount, Account toAccount, double amount) {
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        this.accountRepository.save(fromAccount);
        this.accountRepository.save(toAccount);

        return this.transactionService.addToTransactionHistory(fromAccount, toAccount, amount, fromAccount.getUser(), "successful");
    }

    @Override
    @Transactional
    public UserTransactionDTO transferMoney(TransactionDTO transactionDTO) throws UserNotFoundException , NotEnoughMoneyInAccountException {
        Account fromAccount = this.accountRepository.findByAccountNumber(transactionDTO.getFromAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account number %s not found", transactionDTO.getFromAccountNumber())));
        Account toAccount = this.accountRepository.findByAccountNumber(transactionDTO.getToAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account number %s not found", transactionDTO.getToAccountNumber())));

        this.checkIfFromAndToAccountAreExpired(fromAccount, toAccount);
        this.checkIfFromAndToAccountAreActive(fromAccount, toAccount);
        this.checkIfFromAccountHasEnoughMoney(fromAccount, toAccount, transactionDTO.getAmount());

        return this.performTransferTransaction(fromAccount, toAccount, transactionDTO.getAmount());
    }
}
