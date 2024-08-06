package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.*;
import com.banquemisr.moneytransactionservice.exception.ErrorResponse;
import com.banquemisr.moneytransactionservice.exception.custom.AccountNotActiveException;
import com.banquemisr.moneytransactionservice.exception.custom.AccountNotFoundException;
import com.banquemisr.moneytransactionservice.exception.custom.NotEnoughMoneyInAccountException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.service.impl.AccountService;import com.banquemisr.moneytransactionservice.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Account Controller", description = "The account controller endpoints")
public class AccountController {
    private final AccountService accountService;
    private final JwtUtils jwtUtils;

    @PostMapping("/create_account")
    @ApiResponse(responseCode = "201", description = "Account creation successful", content = {@Content(schema = @Schema(implementation = AccountDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "409", description = "Account already exists", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    public ResponseEntity<AccountDTO> createAccount(@RequestHeader("Authorization") String authorizationHeader, @RequestBody AccountDTO accountDTO) {
        String email = jwtUtils.getEmailFromHeader(authorizationHeader);
        return new ResponseEntity<>(
                this.accountService.createAccount(accountDTO, email),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Get user Account Balance")
    @ApiResponse(responseCode = "200", description = "Account's balance retrieved successfully", content = {@Content(schema = @Schema(implementation = Double.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", description = "Account not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @GetMapping("/balance")
    public ResponseEntity<Double> getUserAccountBalance(@RequestHeader(value = "Authorization") String authorizationHeader, @RequestBody AccountNumberDTO accountDTO) throws UserNotFoundException {
        String email = jwtUtils.getEmailFromHeader(authorizationHeader);
        return new ResponseEntity<>(
                accountService.getUserAccountBalance(accountDTO.getAccountNumber(), email),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Transfer money to another account and create a transaction ticket")
    @ApiResponse(responseCode = "200", description = "Money transferred successfully", content = {@Content(schema = @Schema(implementation = UserTransactionDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Account not expired/inactive", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", description = "Account not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @PostMapping("/transfer")
    public ResponseEntity<UserTransactionDTO> transferMoney(@RequestBody TransactionDTO transactionDTO) throws UserNotFoundException, NotEnoughMoneyInAccountException, AccountNotFoundException, AccountNotActiveException {
        return new ResponseEntity<>(
                this.accountService.transferMoney(transactionDTO),
                HttpStatus.CREATED
        );
    }
}
