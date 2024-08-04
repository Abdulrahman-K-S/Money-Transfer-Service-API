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


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Account Controller", description = "The account controller endpoints")
public class AccountController {
    private final AccountService accountService;
    private final JwtUtils jwtUtils;

    @PostMapping("/create_account")
    public AccountDTO createAccount(@RequestHeader("Authorization") String authorizationHeader, @RequestBody AccountDTO accountDTO) {
        String email = jwtUtils.getEmailFromHeader(authorizationHeader);
        return this.accountService.createAccount(accountDTO, email);
    }

    @Operation(summary = "Get user Account Balance")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @GetMapping("/balance")
    public double getUserAccountBalance(@RequestHeader(value = "Authorization") String authorizationHeader, @RequestBody AccountNumberDTO accountDTO) throws UserNotFoundException {
        String token = jwtUtils.extractTokenFromHeader(authorizationHeader);
        String email = jwtUtils.getUserNameFromJwtToken(token);
        return accountService.getUserAccountBalance(accountDTO.getAccountNumber(), email);
    }

    @Operation(summary = "Transfer money to another account and create a transaction ticket")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @PostMapping("/transfer")
    public ResponseEntity<UserTransactionDTO> transferMoney(@RequestBody TransactionDTO transactionDTO) throws UserNotFoundException, NotEnoughMoneyInAccountException, AccountNotFoundException, AccountNotActiveException {
        return new ResponseEntity<>(
                this.accountService.transferMoney(transactionDTO),
                HttpStatus.CREATED
        );
    }
}
