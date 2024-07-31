package com.banquemisr.moneytransactionservice.dto;

import com.banquemisr.moneytransactionservice.dto.enums.AccountCurrency;
import com.banquemisr.moneytransactionservice.dto.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
    private long id;
    private String accountName;
    private String accountDescription;
    private AccountType accountType;
    private AccountCurrency accountCurrency;
    private double balance;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AccountDTO ToDTO() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(id);
        accountDTO.setAccountName(accountName);
        accountDTO.setAccountDescription(accountDescription);
        accountDTO.setAccountType(accountType);
        accountDTO.setAccountCurrency(accountCurrency);
        accountDTO.setBalance(balance);
        accountDTO.setIsActive(isActive);
        accountDTO.setCreatedAt(createdAt);
        accountDTO.setUpdatedAt(updatedAt);
        return accountDTO;
    }
}

