package com.banquemisr.moneytransactionservice.exception.custom;

public class FavoriteRecipientAlreadyExistsException extends RuntimeException {
    private String message;

    public FavoriteRecipientAlreadyExistsException() {}

    public FavoriteRecipientAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
