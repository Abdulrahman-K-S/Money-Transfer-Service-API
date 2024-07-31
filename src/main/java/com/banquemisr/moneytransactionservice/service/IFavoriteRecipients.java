package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.dto.AddFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.model.FavoriteRecipients;

public interface IFavoriteRecipients {
    /**
     * Adds the recipient to the favorites
     *
     * @param favoriteRecipientDTO The details of the recipient @{@link AddFavoriteRecipientDTO}
     * @return The favorite recipients details @{@link FavoriteRecipients}
     * */
    public FavoriteRecipients addFavoriteRecipient(AddFavoriteRecipientDTO favoriteRecipientDTO);
}
