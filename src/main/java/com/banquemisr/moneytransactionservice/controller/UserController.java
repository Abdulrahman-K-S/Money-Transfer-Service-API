package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.CurrencyRateDTO;
import com.banquemisr.moneytransactionservice.dto.CurrencyToFromRateDTO;
import com.banquemisr.moneytransactionservice.service.IUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final IUser userService;

    @Operation(summary = "Changes rate to EGP")
    @ApiResponse(responseCode = "200", description = "Rate changed successfully", content = {@Content(schema = @Schema(implementation = Double.class), mediaType = "application/json")})
    @GetMapping("/ratetoEGP")
    public ResponseEntity<Double> getRateToEGP(@RequestBody CurrencyRateDTO currencyRateDTO) {
        return new ResponseEntity<>(
                this.userService.getRateToEGP(currencyRateDTO),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Changes rate from EGP")
    @ApiResponse(responseCode = "200", description = "Rate changed successfully", content = {@Content(schema = @Schema(implementation = Double.class), mediaType = "application/json")})
    @GetMapping("/ratefromEGP")
    public ResponseEntity<Double> getRateFromEGP(@RequestBody CurrencyRateDTO currencyRateDTO) {
        return new ResponseEntity<>(
                this.userService.getRateFromEGP(currencyRateDTO),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Changes rate to inputted currencies")
    @ApiResponse(responseCode = "200", description = "Rate changed successfully", content = {@Content(schema = @Schema(implementation = Double.class), mediaType = "application/json")})
    @GetMapping("/rate")
    public ResponseEntity<Double> getRate(@RequestBody CurrencyToFromRateDTO currencyToFromRateDTO) {
        return new ResponseEntity<>(
                this.userService.getRate(currencyToFromRateDTO),
                HttpStatus.OK
        );
    }
}