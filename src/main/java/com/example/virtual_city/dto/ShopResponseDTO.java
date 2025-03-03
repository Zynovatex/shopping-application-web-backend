package com.example.virtual_city.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponseDTO {
    private Long id;
    private String name;
    private String category;
    private boolean isClosed;
    private String type; // Supermarket, Grocery Store, etc.
    private boolean offersDelivery;
    private boolean offersTakeaway;
    private String status; // Open/Closed
    private String imageUrl;
    private List<ProductResponseDTO> products; // List of products
}
