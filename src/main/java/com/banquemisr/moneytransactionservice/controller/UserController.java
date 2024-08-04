package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.enums.Country;
import com.banquemisr.moneytransactionservice.dto.enums.CurrencyRate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserController {
    public Country country;

    public double getRate(String currency, int amount) {

        CurrencyRate currencyRate = CurrencyRate.fromCurrencyCode(currency);
        assert currencyRate != null;
        double rate = currencyRate.getRate();
            return rate * amount;

    }
}