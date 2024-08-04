package com.banquemisr.moneytransactionservice.exception.custom;

public class NotEnoughMoneyInAccountException extends RuntimeException{
    private String message;

    public NotEnoughMoneyInAccountException() {}

    public NotEnoughMoneyInAccountException(String msg) {
        super(msg);
        this.message = msg;
    }
}

