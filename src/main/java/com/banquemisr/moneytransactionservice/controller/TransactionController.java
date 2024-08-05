package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.TransactionDTO;
import com.banquemisr.moneytransactionservice.service.ITransaction;
import com.banquemisr.moneytransactionservice.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    private final JwtUtils jwtUtils;

    @GetMapping("transactions")
    @Operation(summary = "Retrieve the transaction history for the user")
    @ApiResponse(responseCode = "200", description = "Transaction history retrieved")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@RequestHeader("Authorization") String authorizationHeader) {
        String email = jwtUtils.getEmailFromHeader(authorizationHeader);
        return new ResponseEntity<>(
                this.transactionService.getUserTransactionHistory(email),
                HttpStatus.OK
        );
    }
}
