package com.example.virtual_city.controller;

import com.example.virtual_city.dto.ForgotPasswordRequest;
import com.example.virtual_city.dto.LoginRequest;
import com.example.virtual_city.dto.RegisterRequest;
import com.example.virtual_city.dto.ResetPasswordRequest;
import com.example.virtual_city.service.AuthService;
import com.example.virtual_city.service.EmailService;
import com.example.virtual_city.service.PasswordResetService;
import com.example.virtual_city.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;
    private final PasswordResetService passwordResetService;
    private final EmailService emailService;


    public AuthController(AuthService authService,TokenService tokenService,
                          PasswordResetService passwordResetService,EmailService emailService) {

        this.authService = authService;
        this.tokenService = tokenService;
        this.passwordResetService = passwordResetService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
    //.2
    @PutMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        tokenService.revokeToken(token);
        return ResponseEntity.ok("Logged out successfully");
    }
    //.2.3
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        System.out.println("Incoming email request: " + request.getEmail()); // ✅ Debug log

        String token = passwordResetService.generateResetToken(request.getEmail());
        String resetLink = "http://localhost:3000/reset-password?token=" + token;
        emailService.sendEmail(request.getEmail(), "Password Reset Request", "Click here to reset: " + resetLink);
        return ResponseEntity.ok("Password reset link sent");
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        boolean success = passwordResetService.resetPassword(request.getToken(), request.getNewPassword());
        return success ? ResponseEntity.ok("Password reset successful") : ResponseEntity.badRequest().body("Invalid or expired token");
    }


}

