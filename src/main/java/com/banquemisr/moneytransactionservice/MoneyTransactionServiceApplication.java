package com.banquemisr.moneytransactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MoneyTransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransactionServiceApplication.class, args);
    }

}
