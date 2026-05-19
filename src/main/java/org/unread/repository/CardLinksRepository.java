package org.unread.repository;

import org.unread.entity.CardLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardLinksRepository extends JpaRepository<CardLinks, Long> {

    public List<CardLinks> findByCardId(Long cardId);

}
