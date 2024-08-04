package com.banquemisr.moneytransactionservice.exception.custom;

public class AccountNotActiveException extends RuntimeException {
    private String message;

    public AccountNotActiveException() {}

    public AccountNotActiveException(String msg) {
        super(msg);
        this.message = msg;
    }
}
