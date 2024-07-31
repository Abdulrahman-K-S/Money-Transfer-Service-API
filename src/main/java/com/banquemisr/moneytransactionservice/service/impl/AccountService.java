package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.exception.custom.NotEnoughMoneyException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;


@Service
public class AccountService {
    private HashMap<Long, Double> debit = new HashMap<>();
    private HashMap<Long, Double> credit = new HashMap<>();
    private HashMap<Long, Double> last = new HashMap<>();
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

    public HashMap<Long, Double> transactionHistory(Long accountNumber) {
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
