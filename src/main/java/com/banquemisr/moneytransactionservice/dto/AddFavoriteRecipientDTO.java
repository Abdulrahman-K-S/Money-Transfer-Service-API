package com.banquemisr.moneytransactionservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddFavoriteRecipientDTO {
    private Long userId;
    private String recipientName;
    private String recipientAccountNumber;
}
