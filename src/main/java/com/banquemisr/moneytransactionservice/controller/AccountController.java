package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.AccountDTO;
import com.banquemisr.moneytransactionservice.dto.UserDTO;
import com.banquemisr.moneytransactionservice.exception.ErrorResponse;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.service.impl.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@Tag(name = "Account Controller", description = "The account controller endpoints")
public class AccountController {
    //    Get the current account balance for the authenticated user
    private AccountService accountService;

    @Operation(summary = "Get Customer Account Balance")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    @GetMapping("/{id}")
    public AccountDTO getUserAccountBalance(@PathVariable Long id) throws UserNotFoundException {
        return accountService.getUserAccountBalance(id);
    }
    @Operation(summary = "Transfer money to another user")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")})
    public void transferMoney(Long fromAccountNumber,Long toAccountNumber,double balance) throws UserNotFoundException {
        accountService.transferMoney(fromAccountNumber,toAccountNumber,balance);
    }
}
