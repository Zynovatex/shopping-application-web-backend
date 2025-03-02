package com.example.virtual_city.dto;

public interface ShopResponseDTO {
    Long getId();
    String getName();
    String getCategory();
    Double getAverageRating();
    Integer getRatingCount();
    Boolean getIsClosed();
    String getType();
    Boolean getOffersDelivery();
    Boolean getOffersTakeaway();
    String getStatus();
    String getImageUrl();
}
