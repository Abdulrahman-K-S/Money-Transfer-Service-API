package com.banquemisr.moneytransactionservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRecipients {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteRecipientId;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String recipientName;

    @Column(nullable = false)
    private String recipientAccountNumber;
}