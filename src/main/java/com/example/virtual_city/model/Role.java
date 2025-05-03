package com.example.virtual_city.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;  // ✅ Role name (e.g., "ADMIN", "SELLER", "BUYER", etc.)

    //Getter
    public String getName() {
        return name;
    }



}



