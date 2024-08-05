package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.enums.Country;
import com.banquemisr.moneytransactionservice.dto.enums.CurrencyRate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserController {
    public Country country;

    public double getRateToEGP(String currency, double amount) {

        CurrencyRate currencyRate = CurrencyRate.fromCurrencyCode(currency);
        assert currencyRate != null;
        double rate = currencyRate.getRate();
            return rate * amount;

    }
    public double getRateFromEGP(String currency, double amount) {

        CurrencyRate currencyRate = CurrencyRate.fromCurrencyCode(currency);
        assert currencyRate != null;
        double rate = currencyRate.getRate();
        return amount / rate;

    }
    public double getRate(String fromCurrency, String toCurrency , double amount) {

//        CurrencyRate fromCurrencyRate = CurrencyRate.fromCurrencyCode(fromCurrency);
//        CurrencyRate toCurrencyRate = CurrencyRate.fromCurrencyCode(toCurrency);
//        assert fromCurrencyRate != null;
//        assert toCurrencyRate != null;
//        double fromRate = fromCurrencyRate.getRate();
//        double toRate = toCurrencyRate.getRate();
//        double temp = fromRate * amount;
//        return temp / toRate;
        double from = getRateToEGP(fromCurrency,amount);
        return getRateFromEGP(toCurrency,from);
    }
}