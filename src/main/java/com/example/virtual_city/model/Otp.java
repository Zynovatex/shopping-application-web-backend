package com.example.virtual_city.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_codes")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String otp;  // ✅ Stores the 6-digit OTP

    @Column(nullable = false)
    private LocalDateTime expirationTime;  // ✅ Stores expiry time (e.g., 5 min)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // ✅ Links OTP to a user

    // Getters & Setters
    public Long getId() { return id; }
    public String getOtp() { return otp; }
    public LocalDateTime getExpirationTime() { return expirationTime; }
    public User getUser() { return user; }

    public void setId(Long id) { this.id = id; }
    public void setOtp(String otp) { this.otp = otp; }
    public void setExpirationTime(LocalDateTime expirationTime) { this.expirationTime = expirationTime; }
    public void setUser(User user) { this.user = user; }
}
