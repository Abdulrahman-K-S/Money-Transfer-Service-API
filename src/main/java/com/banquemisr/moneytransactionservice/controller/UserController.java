package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.CurrencyRateDTO;
import com.banquemisr.moneytransactionservice.dto.CurrencyToFromRateDTO;
import com.banquemisr.moneytransactionservice.service.IUser;
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

    @GetMapping("/ratetoEGP")
    public ResponseEntity<Double> getRateToEGP(@RequestBody CurrencyRateDTO currencyRateDTO) {
        return new ResponseEntity<>(
                this.userService.getRateToEGP(currencyRateDTO),
                HttpStatus.OK
        );
    }

    @GetMapping("/ratefromEGP")
    public ResponseEntity<Double> getRateFromEGP(@RequestBody CurrencyRateDTO currencyRateDTO) {
        return new ResponseEntity<>(
                this.userService.getRateFromEGP(currencyRateDTO),
                HttpStatus.OK
        );
    }

    @GetMapping("/rate")
    public ResponseEntity<Double> getRate(@RequestBody CurrencyToFromRateDTO currencyToFromRateDTO) {
        return new ResponseEntity<>(
                this.userService.getRate(currencyToFromRateDTO),
                HttpStatus.OK
        );
    }
}