package com.banquemisr.moneytransactionservice.exception.custom;

public class FavoriteRecipientNotFoundException extends RuntimeException {
    private String message;

    public FavoriteRecipientNotFoundException() {}

    public FavoriteRecipientNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
