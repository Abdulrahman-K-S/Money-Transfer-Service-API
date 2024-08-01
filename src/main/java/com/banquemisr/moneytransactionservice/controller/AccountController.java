package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.UserDTO;
import com.banquemisr.moneytransactionservice.dto.UserIdDTO;
import com.banquemisr.moneytransactionservice.exception.ErrorResponse;
import com.banquemisr.moneytransactionservice.exception.custom.NoTransactionsMadeException;
import com.banquemisr.moneytransactionservice.exception.custom.NotEnoughMoneyException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.service.impl.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Account Controller", description = "The account controller endpoints")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create_account")
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO) {
        return this.accountService.createAccount(accountDTO);
    }

    @Operation(summary = "Get user Account Balance")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @GetMapping("/balance")
    public double getUserAccountBalance(@RequestBody UserIdDTO user) throws UserNotFoundException {
        return accountService.getUserAccountBalance(user.getUserId());
    }
    @Operation(summary = "Transfer money to another user")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @PostMapping("/transfer")
    public void transferMoney(Long fromAccountNumber,Long toAccountNumber,double balance) throws UserNotFoundException , NotEnoughMoneyException {
        accountService.transferMoney(fromAccountNumber,toAccountNumber,balance);
    }
    @Operation(summary = "Get user transaction history")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @GetMapping("/transactions")
    public HashMap<Long, Double> transactionHistory(Long AccountNumber) throws UserNotFoundException , NoTransactionsMadeException {
        return accountService.transactionHistory(AccountNumber);
    }
}
