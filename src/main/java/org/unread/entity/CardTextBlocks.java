package org.unread.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CardTextBlocks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardTextBlocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Cards card;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
