package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    public AccountDTO getUserAccountBalance(Long id) throws UserNotFoundException {
        AccountDTO account = this.getUserAccountBalance(id);

        return account.ToDTO();
    }

    public void transferMoney(Long fromAccountNumber, Long toAccountNumber, double balance) {
        AccountDTO fromAccount = this.getUserAccountBalance(fromAccountNumber);
        AccountDTO toAccount = this.getUserAccountBalance(toAccountNumber);
        if (fromAccount.getBalance() >= balance) {
            fromAccount.setBalance(fromAccount.getBalance() - balance);
            toAccount.setBalance(toAccount.getBalance() + balance);
        }
    }
}
