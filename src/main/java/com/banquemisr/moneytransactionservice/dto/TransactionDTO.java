package com.banquemisr.moneytransactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private String fromAccountNumber;
    private String toAccountNumber;
    private Double amount;
}
