package org.unread.controller;

import org.unread.dto.CardResponseDTO;
import org.unread.security.AuthenticatedUser;
import org.unread.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> getCards(
            @AuthenticationPrincipal AuthenticatedUser principal) {
        return ResponseEntity.ok(cardService.getCardsByUserId(principal.getUserId()));
    }




}
