package com.banquemisr.moneytransactionservice.dto;

import com.banquemisr.moneytransactionservice.dto.enums.AccountCurrency;
import com.banquemisr.moneytransactionservice.dto.enums.AccountType;
import com.banquemisr.moneytransactionservice.model.Account;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class AccountDTO extends Account {
    private long id;
    private String accountName;
    private String accountDescription;
    private AccountType accountType;
    private AccountCurrency accountCurrency;
    private double balance;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

