package com.banquemisr.moneytransactionservice.service.impl;

import com.banquemisr.moneytransactionservice.dto.AddFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.dto.ViewFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.exception.custom.*;
import com.banquemisr.moneytransactionservice.model.FavoriteRecipients;
import com.banquemisr.moneytransactionservice.model.User;
import com.banquemisr.moneytransactionservice.repository.AccountRepository;
import com.banquemisr.moneytransactionservice.repository.FavoriteRecipientsRepository;
import com.banquemisr.moneytransactionservice.service.IFavoriteRecipients;
import com.banquemisr.moneytransactionservice.service.IUser;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "favorites")
public class FavoriteRecipientsService implements IFavoriteRecipients {

    private final FavoriteRecipientsRepository favoriteRecipientsRepository;
    private final IUser userService;
    private final AccountRepository accountRepository;

    @Override
    public void checkIfAccountExistsAndNotAlreadyInFavorites(AddFavoriteRecipientDTO favoriteRecipientDTO, String email) throws AccountNotFoundException, FavoriteRecipientAlreadyExistsException {
        if (Boolean.FALSE.equals(this.accountRepository.existsByAccountNumber(favoriteRecipientDTO.getRecipientAccountNumber()))) {
            throw new AccountNotFoundException(String.format("Account number %s not found", favoriteRecipientDTO.getRecipientAccountNumber()));
        } else if (Boolean.TRUE.equals(this.favoriteRecipientsRepository.existsFavoriteRecipientsByRecipientAccountNumberAndUser_Email(favoriteRecipientDTO.getRecipientAccountNumber(), email))) {
            throw new FavoriteRecipientAlreadyExistsException("This recipient already exists in favorites");
        }
    }

    @Override
    @Transactional
    @CacheEvict(key = "#email")
    public ViewFavoriteRecipientDTO addFavoriteRecipient(AddFavoriteRecipientDTO favoriteRecipientDTO, String email) throws UserNotFoundException, AccountNotFoundException, FavoriteRecipientAlreadyExistsException {
        User user = this.userService.getUserIfExistsByEmail(email);
        this.checkIfAccountExistsAndNotAlreadyInFavorites(favoriteRecipientDTO, email);

        FavoriteRecipients recipient = FavoriteRecipients
                .builder()
                .user(user)
                .recipientName(favoriteRecipientDTO.getRecipientName())
                .recipientAccountNumber(favoriteRecipientDTO.getRecipientAccountNumber())
                .build();

        return this.favoriteRecipientsRepository.save(recipient).toDTO();
    }

    @Override
    @Transactional
    @Cacheable(key = "#email")
    public List<ViewFavoriteRecipientDTO> getAllFavoriteRecipients(String email) {
        return this.favoriteRecipientsRepository
                .findByUser_Email(email)
                .stream()
                .map(FavoriteRecipients::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(key = "#email")
    public void deleteFavoriteRecipient(Long favoriteRecipientId, String email) throws FavoriteRecipientNotFoundException, FavoriteRecipientAccessNotAllowedException {
        if (Boolean.FALSE.equals(this.favoriteRecipientsRepository.existsByFavoriteRecipientId(favoriteRecipientId))) {
            throw new FavoriteRecipientNotFoundException(String.format("Favorite recipient with id %s not found", favoriteRecipientId));
        } else if (Boolean.FALSE.equals(this.favoriteRecipientsRepository.existsFavoriteRecipientsByFavoriteRecipientIdAndUser_Email(favoriteRecipientId, email))) {
            throw new FavoriteRecipientAccessNotAllowedException(String.format("User with email %s isn't the favorite recipient owner", email));
        }
        this.favoriteRecipientsRepository.delete(this.favoriteRecipientsRepository.findByFavoriteRecipientId(favoriteRecipientId));
    }
}
