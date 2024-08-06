package com.banquemisr.moneytransactionservice.exception.custom;

public class RefreshTokenExpiredException extends RuntimeException {
    private String message;

    public RefreshTokenExpiredException() {}

    public RefreshTokenExpiredException(String msg) {
        super(msg);
        this.message = msg;
    }
}
