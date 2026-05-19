package org.unread.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardLinksDTO {
    private Long id;
    private LinksDTO links;
    private String label;
    private String description;
    private Short displayOrder;
}
