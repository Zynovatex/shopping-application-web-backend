package com.example.virtual_city.controller;

import com.example.virtual_city.dto.*;
import com.example.virtual_city.model.AdminStatus;
import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.UserRepository;
import com.example.virtual_city.service.AuthService;
import com.example.virtual_city.service.EmailService;
import com.example.virtual_city.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Update lastLogin before generating token
        user.setLastLogin(java.time.LocalDateTime.now().toString());
        userRepository.save(user);

        String token = jwtUtil.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("allowedModules", user.getAllowedModules());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody ForgotPasswordRequest request) {
        String response = authService.sendOtp(request.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password-otp")
    public ResponseEntity<String> resetPasswordWithOtp(@RequestBody ResetPasswordRequest request) {
        boolean success = authService.resetPasswordWithOtp(
                request.getEmail(),
                request.getOtp(),
                request.getNewPassword()
        );
        return success ? ResponseEntity.ok("Password reset successful")
                : ResponseEntity.badRequest().body("Invalid OTP");
    }

    // Set password for invited admins using status check
    @PostMapping("/set-password")
    public ResponseEntity<String> setPassword(@RequestBody SetPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if password is already set by verifying status
        if (user.getStatus() != AdminStatus.PENDING) {
            return ResponseEntity.status(403).body("Password already set.");
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(AdminStatus.ACTIVE);
        userRepository.save(user);

        return ResponseEntity.ok("Password set successfully!");
    }
}
