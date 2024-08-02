package com.banquemisr.moneytransactionservice.exception.custom;

public class AccountAccessNotAllowedException extends RuntimeException {
    private String message;

    public AccountAccessNotAllowedException() {}

    public AccountAccessNotAllowedException(String msg) {
        super(msg);
        this.message = msg;
    }
}
