package com.banquemisr.moneytransactionservice.repository;

import com.banquemisr.moneytransactionservice.model.FavoriteRecipients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRecipientsRepository extends JpaRepository<FavoriteRecipients, Integer> {
    FavoriteRecipients findByFavoriteRecipientId(Long favoriteRecipientId);

    List<FavoriteRecipients> findByUser_Email(String email);

    Boolean existsByFavoriteRecipientId(Long favoriteRecipientId);
    Boolean existsFavoriteRecipientsByRecipientAccountNumberAndUser_Email(String recipientAccountNumber, String email);
    Boolean existsFavoriteRecipientsByFavoriteRecipientIdAndUser_Email(Long favoriteRecipientId, String email);
}
