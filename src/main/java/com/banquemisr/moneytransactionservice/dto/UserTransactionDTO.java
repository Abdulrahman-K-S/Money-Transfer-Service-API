package com.banquemisr.moneytransactionservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTransactionDTO {
    private Long userId;
    private Long transactionId;
    private String status;
}
