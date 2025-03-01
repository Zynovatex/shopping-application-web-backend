package com.example.virtual_city.dto;


public class ResetPasswordRequest {
    private String email;
    private String otp;
    private String newPassword;

    // ✅ Constructor (Optional)
    public ResetPasswordRequest() {}

    // ✅ Getters
    public String getEmail() {
        return email;
    }

    public String getOtp() {
        return otp;
    }

    public String getNewPassword() {
        return newPassword;
    }

    // ✅ Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

