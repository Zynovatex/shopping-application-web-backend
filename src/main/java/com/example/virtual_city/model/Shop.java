package com.example.virtual_city.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String category;

    private boolean isClosed;

    private String type; // Supermarket, Grocery Store, etc.

    private boolean offersDelivery;

    private boolean offersTakeaway;

    private String status; // Open/Closed

    private String imageUrl; // Image URL for display
}
