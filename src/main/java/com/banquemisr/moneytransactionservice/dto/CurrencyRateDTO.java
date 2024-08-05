package com.banquemisr.moneytransactionservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyRateDTO {
    private String currency;
    private double amount;
}
