package com.example.virtual_city.dto;




public class LoginRequest {
    private String email;
    private String password;

    // Manually adding getter methods
    public String getEmail() {
        return email;
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
}
