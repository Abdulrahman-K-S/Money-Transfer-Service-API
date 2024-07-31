package com.banquemisr.moneytransactionservice.repository;

import com.banquemisr.moneytransactionservice.model.FavoriteRecipients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRecipientsRepository extends JpaRepository<FavoriteRecipients, Integer> {
    List<FavoriteRecipients> findByUser_CustomerId(Long userId);
}
