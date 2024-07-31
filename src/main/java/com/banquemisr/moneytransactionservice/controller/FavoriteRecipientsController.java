package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.AddFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.model.FavoriteRecipients;
import com.banquemisr.moneytransactionservice.service.IFavoriteRecipients;
import com.banquemisr.moneytransactionservice.service.impl.FavoriteRecipientsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Favorite Recipient Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FavoriteRecipientsController {

    private final IFavoriteRecipients favoriteRecipientsService;

    @PostMapping("/favorites")
    public ResponseEntity<FavoriteRecipients> addFavoriteRecipient(@RequestBody AddFavoriteRecipientDTO favoriteRecipient) {
        return ResponseEntity.ok(favoriteRecipientsService.addFavoriteRecipient(favoriteRecipient));
    }
}
