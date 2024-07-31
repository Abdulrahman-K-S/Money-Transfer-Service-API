package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.AddFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.dto.LoginResponseDTO;
import com.banquemisr.moneytransactionservice.dto.UserIdDTO;
import com.banquemisr.moneytransactionservice.dto.ViewFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.model.FavoriteRecipients;
import com.banquemisr.moneytransactionservice.service.IFavoriteRecipients;
import com.banquemisr.moneytransactionservice.service.impl.FavoriteRecipientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Favorite Recipient Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FavoriteRecipientsController {

    private final IFavoriteRecipients favoriteRecipientsService;

    @Operation(summary = "Add recipient to favorites")
    @PostMapping("/favorites")
    public ResponseEntity<FavoriteRecipients> addFavoriteRecipient(@RequestBody AddFavoriteRecipientDTO favoriteRecipient) {
        return ResponseEntity.ok(favoriteRecipientsService.addFavoriteRecipient(favoriteRecipient));
    }

    @Operation(summary = "Retrieve all the favorite recipients")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ViewFavoriteRecipientDTO.class), mediaType = "application/json")})
    @GetMapping("/favorites")
    public ResponseEntity<List<ViewFavoriteRecipientDTO>> getFavoriteRecipients(@RequestBody UserIdDTO user) {
        return ResponseEntity.ok(this.favoriteRecipientsService.getAllFavoriteRecipients(user));
    }
}
