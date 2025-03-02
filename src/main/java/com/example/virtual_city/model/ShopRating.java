package com.example.virtual_city.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "shop_ratings")
public class ShopRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    private double rating; // Individual rating (1.0 - 5.0)

    private String review; // Optional user feedback
}
