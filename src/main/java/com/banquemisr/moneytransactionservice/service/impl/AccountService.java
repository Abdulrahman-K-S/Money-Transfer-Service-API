package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.exception.custom.NoTransactionsMadeException;
import com.banquemisr.moneytransactionservice.exception.custom.NotEnoughMoneyException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.model.Account;
import com.banquemisr.moneytransactionservice.repository.AccountRepository;
import com.banquemisr.moneytransactionservice.service.IAccount;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;


@Service
@AllArgsConstructor
public class AccountService implements IAccount {
    private final AccountRepository accountRepository;
    private HashMap<Long, Double> debit = new HashMap<>();
    private HashMap<Long, Double> credit = new HashMap<>();
    private HashMap<Long, Double> last = new HashMap<>();

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

    public AccountDTO getUserAccountBalance(Long id) throws UserNotFoundException {
        AccountDTO account = this.getUserAccountBalance(id);

        return account.ToDTO();
    }

    public void transferMoney(Long fromAccountNumber,Long toAccountNumber,double balance) throws UserNotFoundException , NotEnoughMoneyException {
        AccountDTO fromAccount = this.getUserAccountBalance(fromAccountNumber);
        AccountDTO toAccount = this.getUserAccountBalance(toAccountNumber);
        if (fromAccount.getBalance() >= balance) {
            fromAccount.setBalance(fromAccount.getBalance() - balance);
            toAccount.setBalance(toAccount.getBalance() + balance);
            debit.put(toAccountNumber, balance);
            credit.put(fromAccountNumber, balance);
        }
    }

    public HashMap<Long, Double> transactionHistory(Long accountNumber) throws UserNotFoundException,NoTransactionsMadeException {
        last.clear();
        debit.forEach((key, value) -> {
            if (Objects.equals(key, accountNumber)) {
                last.put(accountNumber, value);
            }
        });
        credit.forEach((key, value) -> {
            if (Objects.equals(key, accountNumber)) {
                last.put(accountNumber, value);
            }
        });
        return last;
    }
}
