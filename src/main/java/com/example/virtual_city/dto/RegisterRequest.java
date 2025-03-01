package com.example.virtual_city.dto;

public class RegisterRequest {
    // Setters
    // Getters
    private String name;
    private String email;
    private String password;

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
}
