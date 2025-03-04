package com.example.virtual_city.dto;

import com.example.virtual_city.model.Role;

public class RegisterRequest {
    // Setters
    // Getters
    private String name;
    private String email;
    private String password;
    private String role;  // ✅ Now role is a string (e.g., "SELLER", "ADMIN")

    // Manually adding getter methods
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() { return role; }  // ✅ Getter for role

    // Manually adding setter methods

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) { this.role = role; }  // ✅ Setter for role
}
