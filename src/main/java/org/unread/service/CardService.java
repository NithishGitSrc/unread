package org.unread.service;

import org.unread.dto.CardResponseDTO;
import org.unread.dto.CardTextBlockDTO;
import org.unread.repository.CardTextBlocksRepository;
import org.unread.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardsRepository cardsRepository;
    private final CardLinksService  cardLinksService;
    private final CardTextBlocksRepository cardTextBlocksRepository;
    public List<CardResponseDTO> getCardsByUserId(Long userId) {
        return cardsRepository.findByUserId(userId).stream()
                .map(card -> CardResponseDTO.builder()
                        .id(card.getId())
                        .title(card.getTitle())
                        .description(card.getDescription())
                        .isActive(card.getIsActive())
                        .createdAt(card.getCreatedAt())
                        .updatedAt(card.getUpdatedAt())
                        .cardLinksDTO(cardLinksService.getCardLinksByCardId(card.getId()))
                        .cardTextBlockDTO(cardTextBlocksRepository.findByCardId(card.getId()).stream().map(response-> CardTextBlockDTO.builder()
                                .id(response.getId())
                                .content(response.getContent())
                                .build()).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
