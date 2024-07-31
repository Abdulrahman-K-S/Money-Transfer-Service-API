package com.banquemisr.moneytransactionservice.exception.custom;

public class NoTransactionsMadeException extends RuntimeException{
        private String message;

        public NoTransactionsMadeException() {}

        public NoTransactionsMadeException(String msg) {
            super(msg);
            this.message = msg;
        }
    }