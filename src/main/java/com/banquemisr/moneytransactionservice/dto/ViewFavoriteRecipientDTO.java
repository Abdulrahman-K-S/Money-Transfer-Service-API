package com.banquemisr.moneytransactionservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewFavoriteRecipientDTO {
    private Long favoriteRecipientId;
    private String recipientName;
    private String recipientAccountNumber;
}
