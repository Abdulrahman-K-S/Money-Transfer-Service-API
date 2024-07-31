package com.banquemisr.moneytransactionservice.exception.custom;

public class NotEnoughMoneyException extends RuntimeException{
        private String message;

        public NotEnoughMoneyException() {}

        public NotEnoughMoneyException(String msg) {
            super(msg);
            this.message = msg;
        }
    }

