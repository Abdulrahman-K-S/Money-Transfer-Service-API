package com.banquemisr.moneytransactionservice.exception.custom;

public class AccountNotFoundException extends RuntimeException {
    private String message;

    public AccountNotFoundException() {}

    public AccountNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
