package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.dto.AddFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.dto.ViewFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.exception.custom.*;
import com.banquemisr.moneytransactionservice.model.FavoriteRecipients;

import java.util.List;

public interface IFavoriteRecipients {

    /**
     * Checks if the account number exists and not already in the favorites list
     *
     * @param favoriteRecipientDTO The recipient's details
     * @param email The user email
     * @throws AccountNotFoundException If account doesn't exist
     * @throws FavoriteRecipientAlreadyExistsException If the recipient is already in the favorites list
     * */
    void checkIfAccountExistsAndNotAlreadyInFavorites(AddFavoriteRecipientDTO favoriteRecipientDTO, String email) throws AccountNotFoundException, FavoriteRecipientAlreadyExistsException;

    /**
     * Adds the recipient to the favorites
     *
     * @param favoriteRecipientDTO The details of the recipient @{@link AddFavoriteRecipientDTO}
     * @return The favorite recipients details @{@link FavoriteRecipients}
     * @throws UserNotFoundException If user doesn't exist
     * @throws AccountNotFoundException If the account doesn't exist
     * @throws FavoriteRecipientAlreadyExistsException If the recipient is already in the favorites list
     * */
    ViewFavoriteRecipientDTO addFavoriteRecipient(AddFavoriteRecipientDTO favoriteRecipientDTO, String email) throws UserNotFoundException, AccountNotFoundException, FavoriteRecipientAlreadyExistsException;

    /**
     * Retrieve all the favorite recipients
     *
     * @param email The user email
     * @return A list of all the favorite recipients @{@link ViewFavoriteRecipientDTO}
     * */
    List<ViewFavoriteRecipientDTO> getAllFavoriteRecipients(String email);

    /**
     * Deletes a recipient from the favorite's list
     *
     * @param favoriteRecipientId The favorite recipient id
     * @param email The user email
     * @throws FavoriteRecipientNotFoundException If the favorite recipient doesn't exist
     * @throws FavoriteRecipientAccessNotAllowedException If the user can't delete the favorite recipient
     * */
    void deleteFavoriteRecipient(Long favoriteRecipientId, String email) throws FavoriteRecipientNotFoundException, FavoriteRecipientAccessNotAllowedException;
}
