package com.example.virtual_city.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopListResponseDTO {
    private Long id;
    private String name;
    private String category;
    private Double averageRating;
    private Long ratingCount; // Use Long instead of Integer
    private Boolean isClosed;
    private String status;
    private String imageUrl;
}
