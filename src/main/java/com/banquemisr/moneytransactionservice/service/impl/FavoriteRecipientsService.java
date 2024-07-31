package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.AddFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.dto.UserIdDTO;
import com.banquemisr.moneytransactionservice.dto.ViewFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.exception.custom.AccountNotFoundException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.model.FavoriteRecipients;
import com.banquemisr.moneytransactionservice.model.User;
import com.banquemisr.moneytransactionservice.repository.AccountRepository;
import com.banquemisr.moneytransactionservice.repository.FavoriteRecipientsRepository;
import com.banquemisr.moneytransactionservice.repository.UserRepository;
import com.banquemisr.moneytransactionservice.service.IFavoriteRecipients;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteRecipientsService implements IFavoriteRecipients {
    private final FavoriteRecipientsRepository favoriteRecipientsRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public FavoriteRecipients addFavoriteRecipient(AddFavoriteRecipientDTO favoriteRecipientDTO) {
        User user = userRepository.findById(favoriteRecipientDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format("User with user id %s not found", favoriteRecipientDTO.getUserId())));
        if (Boolean.FALSE.equals(this.accountRepository.existsByAccountNumber(favoriteRecipientDTO.getRecipientAccountNumber()))) {
            throw new AccountNotFoundException(String.format("Account number %s not found", favoriteRecipientDTO.getRecipientAccountNumber()));
        }

        FavoriteRecipients recipient = FavoriteRecipients
                .builder()
                .user(user)
                .recipientName(favoriteRecipientDTO.getRecipientName())
                .recipientAccountNumber(favoriteRecipientDTO.getRecipientAccountNumber())
                .build();

        return favoriteRecipientsRepository.save(recipient);
    }

    @Override
    public List<ViewFavoriteRecipientDTO> getAllFavoriteRecipients(UserIdDTO user) {
        return this.favoriteRecipientsRepository
                .findByUser_CustomerId(user.getUserId())
                .stream()
                .map(FavoriteRecipients::toDTO)
                .collect(Collectors.toList());
    }
}
