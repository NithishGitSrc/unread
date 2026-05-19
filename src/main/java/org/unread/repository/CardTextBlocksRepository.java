package org.unread.repository;

import org.unread.entity.CardTextBlocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardTextBlocksRepository extends JpaRepository<CardTextBlocks, Long> {
    List<CardTextBlocks> findByCardId(Long cardId);
}
