package com.example.virtual_city.dto;



public class ResetPasswordRequest {
    private String token;
    private String newPassword;

    // Constructor (Optional)
   // public ResetPasswordRequest() {}

    // Getters & Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
