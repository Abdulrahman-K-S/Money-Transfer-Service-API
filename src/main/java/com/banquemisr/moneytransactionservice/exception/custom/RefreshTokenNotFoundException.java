package com.banquemisr.moneytransactionservice.exception.custom;

public class RefreshTokenNotFoundException extends RuntimeException {
    private String message;

    public RefreshTokenNotFoundException() {}

    public RefreshTokenNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
