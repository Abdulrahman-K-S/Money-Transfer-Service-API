package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.CurrencyRateDTO;
import com.banquemisr.moneytransactionservice.dto.CurrencyToFromRateDTO;
import com.banquemisr.moneytransactionservice.dto.enums.CurrencyRate;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.model.User;
import com.banquemisr.moneytransactionservice.repository.UserRepository;
import com.banquemisr.moneytransactionservice.service.IUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUser {

    private final UserRepository userRepository;


    @Override
    public User getUserIfExistsById(Long userId) {
        return this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with user id %d", userId)));
    }

    @Override
    public User getUserIfExistsByEmail(String email) {
        return this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with user email %s not found", email)));
    }

    @Override
    public Double getRateToEGP(CurrencyRateDTO currencyRateDTO) {
        CurrencyRate currencyRate = CurrencyRate.fromCurrencyCode(currencyRateDTO.getCurrency());
        if (currencyRate == null) {
            return null;
        }
        return currencyRate.getRate() * currencyRateDTO.getAmount();
    }

    @Override
    public Double getRateFromEGP(CurrencyRateDTO currencyRateDTO) {
        CurrencyRate currencyRate = CurrencyRate.fromCurrencyCode(currencyRateDTO.getCurrency());
        if (currencyRate == null) {
            return null;
        }
        return currencyRateDTO.getAmount() / currencyRate.getRate();
    }

    @Override
    public Double getRate(CurrencyToFromRateDTO currencyToFromRateDTO) {
        Double fromAmount = getRateToEGP(CurrencyRateDTO.builder()
                .currency(currencyToFromRateDTO.getFromCurrency()).amount(currencyToFromRateDTO.getAmount())
                .build());
        return getRateFromEGP(CurrencyRateDTO.builder()
                .currency(currencyToFromRateDTO.getToCurrency())
                .amount(fromAmount)
                .build());
    }
}
