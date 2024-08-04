package com.banquemisr.moneytransactionservice.dto;

import com.banquemisr.moneytransactionservice.dto.enums.AccountCurrency;

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
    private String accountNumber;
    private String expiryDate;
    private String cvv;
    private AccountCurrency accountCurrency;
    private double balance;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String otp;
}

