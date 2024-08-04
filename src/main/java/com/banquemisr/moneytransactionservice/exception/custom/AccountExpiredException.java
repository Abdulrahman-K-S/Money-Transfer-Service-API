package com.banquemisr.moneytransactionservice.exception.custom;

public class AccountExpiredException extends RuntimeException {
    private String message;

    public AccountExpiredException() {}

    public AccountExpiredException(String msg) {
        super(msg);
        this.message = msg;
    }
}
