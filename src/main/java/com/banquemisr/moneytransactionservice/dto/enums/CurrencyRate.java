package com.banquemisr.moneytransactionservice.dto.enums;

public enum CurrencyRate {
    USD_TO_EGP(48.63),  // US Dollar to Egyptian Pound
    EUR_TO_EGP(53.72),  // Euro to Egyptian Pound
    GBP_TO_EGP(62.33),  // British Pound to Egyptian Pound
    JPY_TO_EGP(0.33),   // Japanese Yen to Egyptian Pound
    AUD_TO_EGP(31.86),  // Australian Dollar to Egyptian Pound
    CAD_TO_EGP(35.16),  // Canadian Dollar to Egyptian Pound
    CHF_TO_EGP(54.50),  // Swiss Franc to Egyptian Pound
    CNY_TO_EGP(6.66),   // Chinese Yuan to Egyptian Pound
    SEK_TO_EGP(4.46),   // Swedish Krona to Egyptian Pound
    NZD_TO_EGP(29.63),  // New Zealand Dollar to Egyptian Pound
    INR_TO_EGP(0.59),   // Indian Rupee to Egyptian Pound
    RUB_TO_EGP(0.54),   // Russian Ruble to Egyptian Pound
    BRL_TO_EGP(10.10),  // Brazilian Real to Egyptian Pound
    ZAR_TO_EGP(2.67),   // South African Rand to Egyptian Pound
    TRY_TO_EGP(1.80),   // Turkish Lira to Egyptian Pound
    SAR_TO_EGP(12.97),  // Saudi Riyal to Egyptian Pound
    AED_TO_EGP(13.24),  // United Arab Emirates Durham to Egyptian Pound
    SGD_TO_EGP(35.89),  // Singapore Dollar to Egyptian Pound
    MYR_TO_EGP(10.73),  // Malaysian Ringgit to Egyptian Pound
    KRW_TO_EGP(0.038);  // South Korean Won to Egyptian Pound

    private final double rate;

    CurrencyRate(double rate) {
        this.rate = rate;
    }
}
