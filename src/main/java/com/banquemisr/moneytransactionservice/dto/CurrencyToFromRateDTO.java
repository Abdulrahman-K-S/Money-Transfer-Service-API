package com.banquemisr.moneytransactionservice.dto;

import lombok.Data;

@Data
public class CurrencyToFromRateDTO {
    private String toCurrency;
    private String fromCurrency;
    private Double amount;
}
