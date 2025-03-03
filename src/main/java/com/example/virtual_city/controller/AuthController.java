package com.example.virtual_city.controller;

import com.example.virtual_city.dto.ForgotPasswordRequest;
import com.example.virtual_city.dto.LoginRequest;
import com.example.virtual_city.dto.RegisterRequest;
import com.example.virtual_city.dto.ResetPasswordRequest;
import com.example.virtual_city.service.AuthService;
import com.example.virtual_city.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;



    public AuthController(AuthService authService, EmailService emailService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }


    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody ForgotPasswordRequest request) {
        String response = authService.sendOtp(request.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password-otp")
    public ResponseEntity<String> resetPasswordWithOtp(@RequestBody ResetPasswordRequest request) {
        boolean success = authService.resetPasswordWithOtp(request.getEmail(), request.getOtp(), request.getNewPassword());
        return success ? ResponseEntity.ok("Password reset successful") : ResponseEntity.badRequest().body("Invalid OTP");
    }



}

