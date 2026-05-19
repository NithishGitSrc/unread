package org.unread.service;

import org.unread.dto.LinksDTO;
import org.unread.repository.LinksRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class LinkService {

        private final LinksRepository linksRepository;

        public LinksDTO getLinkById(Long id) {
            return linksRepository.findById(id)
                    .map(link -> new LinksDTO(link.getId(), link.getUrl(), link.getCreatedAt()))
                    .orElse(null);
        }

}
