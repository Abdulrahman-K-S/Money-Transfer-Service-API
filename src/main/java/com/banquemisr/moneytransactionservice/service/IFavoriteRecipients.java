package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.dto.AddFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.dto.UserDTO;
import com.banquemisr.moneytransactionservice.dto.UserIdDTO;
import com.banquemisr.moneytransactionservice.dto.ViewFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.model.FavoriteRecipients;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IFavoriteRecipients {
    /**
     * Adds the recipient to the favorites
     *
     * @param favoriteRecipientDTO The details of the recipient @{@link AddFavoriteRecipientDTO}
     * @return The favorite recipients details @{@link FavoriteRecipients}
     * */
    public FavoriteRecipients addFavoriteRecipient(AddFavoriteRecipientDTO favoriteRecipientDTO);

    /**
     * Retrieve all the favorite recipients
     *
     * @param user The user ID
     * @return A list of all the favorite recipients @{@link ViewFavoriteRecipientDTO}
     * */
    public List<ViewFavoriteRecipientDTO> getAllFavoriteRecipients(UserIdDTO user);

    /**
     * Deletes a recipient from the favorite's list
     *
     * @param favoriteRecipientId The favorite recipient id
     * */
    public void deleteFavoriteRecipient(Long favoriteRecipientId);
}
