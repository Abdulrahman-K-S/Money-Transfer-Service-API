package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.dto.UserIdDTO;
import com.banquemisr.moneytransactionservice.service.ITransaction;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "Transaction Controller", description = "The transaction controller endpoints")
public class TransactionController {

    private final ITransaction transactionService;

    @GetMapping("transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@RequestBody UserIdDTO userId) {
        return new ResponseEntity<>(
                this.transactionService.getUserTransactionHistory(userId),
                HttpStatus.OK
        );
    }
}
