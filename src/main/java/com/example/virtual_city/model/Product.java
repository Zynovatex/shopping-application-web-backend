package com.example.virtual_city.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private double price;

    private String imageUrl; // Product image

    // Many-to-One relationship with Shop
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
}
