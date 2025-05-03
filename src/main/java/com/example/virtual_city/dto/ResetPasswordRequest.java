package com.example.virtual_city.dto;

public class ResetPasswordRequest {
    private String email;
    private String otp; // Optional: Only needed for OTP reset
    private String newPassword;
    private String password; // ✅ This is the super admin's current password

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

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
