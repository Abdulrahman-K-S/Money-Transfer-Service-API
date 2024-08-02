package com.banquemisr.moneytransactionservice.controller;

import com.banquemisr.moneytransactionservice.dto.AddFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.dto.ViewFavoriteRecipientDTO;
import com.banquemisr.moneytransactionservice.exception.ErrorResponse;
import com.banquemisr.moneytransactionservice.exception.custom.AccountNotFoundException;
import com.banquemisr.moneytransactionservice.exception.custom.FavoriteRecipientAlreadyExistsException;
import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.service.IFavoriteRecipients;
import com.banquemisr.moneytransactionservice.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Favorite Recipient Controller",
        description = "The controller that's responsible for the recipient favorites"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FavoriteRecipientsController {

    private final IFavoriteRecipients favoriteRecipientsService;
    private final JwtUtils jwtUtils;

    @Operation(summary = "Add recipient to favorites")
    @ApiResponse(responseCode = "201", description = "Recipient added to favorites successfully", content = @Content(schema = @Schema(implementation = ViewFavoriteRecipientDTO.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "401", description = "User is not authenticated", content = {@Content(examples = {})})
    @ApiResponse(responseCode = "403", content = {@Content(examples = {})})
    @ApiResponse(responseCode = "404", description = "User/account not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    @PostMapping("/favorites")
    public ResponseEntity<ViewFavoriteRecipientDTO> addFavoriteRecipient(@RequestHeader("Authorization") String authorizationHeader, @RequestBody AddFavoriteRecipientDTO favoriteRecipient) throws UserNotFoundException, AccountNotFoundException, FavoriteRecipientAlreadyExistsException {
        String email = jwtUtils.getEmailFromHeader(authorizationHeader);
        return new ResponseEntity<>(
                favoriteRecipientsService.addFavoriteRecipient(favoriteRecipient, email),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Retrieve all the favorite recipients")
    @ApiResponse(responseCode = "200", description = "", content = {@Content(schema = @Schema(implementation = ViewFavoriteRecipientDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "401", description = "User is not authenticated", content = {@Content(examples = {})})
    @GetMapping("/favorites")
    public ResponseEntity<List<ViewFavoriteRecipientDTO>> getFavoriteRecipients(@RequestHeader("Authorization") String authorizationHeader) {
        String email = jwtUtils.getEmailFromHeader(authorizationHeader);
        return new ResponseEntity<>(
                this.favoriteRecipientsService.getAllFavoriteRecipients(email),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Deletes a recipient from the favorite's list")
    @ApiResponse(responseCode = "200", description = "Recipient removed from favorites", content = {@Content(examples = {})})
    @ApiResponse(responseCode = "401", description = "User is not authenticated", content = {@Content(examples = {})})
    @ApiResponse(responseCode = "403", description = "User's not authorized to delete this", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    @ApiResponse(responseCode = "404", description = "Recipient not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<String> deleteFavoriteRecipient(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        String email = jwtUtils.getEmailFromHeader(authorizationHeader);
        favoriteRecipientsService.deleteFavoriteRecipient(id, email);
        return new ResponseEntity<>(
                "Deleted recipient",
                HttpStatus.OK
        );
    }
}
