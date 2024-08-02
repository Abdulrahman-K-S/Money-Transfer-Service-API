package com.banquemisr.moneytransactionservice.exception.custom;

public class FavoriteRecipientAccessNotAllowedException extends RuntimeException {
    private String message;

    public FavoriteRecipientAccessNotAllowedException() {}

    public FavoriteRecipientAccessNotAllowedException(String msg) {
        super(msg);
        this.message = msg;
    }
}
