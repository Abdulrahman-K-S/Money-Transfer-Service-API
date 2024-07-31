package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.model.Account;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    public AccountDTO getUserAccountBalance(Long id) throws UserNotFoundException {
        AccountDTO account = this.getUserAccountBalance(id);

        return account.ToDTO();
    }
}
