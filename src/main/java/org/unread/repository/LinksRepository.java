package org.unread.repository;

import org.unread.entity.Links;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinksRepository extends JpaRepository<Links,Long> {
    public Optional<Links> findById(Long id);
}
