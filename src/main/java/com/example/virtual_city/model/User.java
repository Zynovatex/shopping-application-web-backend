package com.example.virtual_city.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")

@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = true;

    // Manually adding getter methods

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // Manually adding setter methods
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setName(String name) {
        this.name = name;
    }
}
