package org.unread.service;

import org.unread.dto.CardLinksDTO;
import org.unread.dto.LinksDTO;
import org.unread.repository.CardLinksRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CardLinksService {

    private final CardLinksRepository cardLinksRepository;
    private final LinkService linkService;

    public List<CardLinksDTO> getCardLinksByCardId(Long cardId) {
        List<CardLinksDTO> cardLinksDTOS = cardLinksRepository.findByCardId(cardId).stream()
                .map(cardLinks -> CardLinksDTO.builder()
                        .id(cardLinks.getId())

                        .links(
                                LinksDTO.builder()
                                        .id(cardLinks.getLink().getId())
                                        .url(cardLinks.getLink().getUrl())
                                        .createdAt(cardLinks.getLink().getCreatedAt())
                                        .build()
                        )
                        .label(cardLinks.getLabel())
                        .description(cardLinks.getDescription())
                        .displayOrder(cardLinks.getDisplayOrder())
                        .build()
                )
                .collect(Collectors.toList());
        return cardLinksDTOS;
    }


}
