package com.example.virtual_city.service;

import com.example.virtual_city.dto.LoginRequest;
import com.example.virtual_city.dto.RegisterRequest;
import com.example.virtual_city.model.AdminStatus;
import com.example.virtual_city.model.Role;
import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.RoleRepository;
import com.example.virtual_city.repository.UserRepository;
import com.example.virtual_city.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final OtpService otpService;
    private final EmailService emailService;
    private final RoleRepository roleRepository;
    private final UserDetailsServiceImpl userDetailsService; // ✅ added

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       AuthenticationManager authenticationManager,
                       OtpService otpService,
                       EmailService emailService,
                       RoleRepository roleRepository,
                       UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.otpService = otpService;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
        this.userDetailsService = userDetailsService; // ✅ injected
    }

    public String registerUser(RegisterRequest request) {
        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + request.getRole()));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setStatus(AdminStatus.ACTIVE);

        userRepository.save(user);
        return "User registered successfully with role: " + user.getRole().getName();
    }

    public String sendOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = otpService.generateOtp(user);
        emailService.sendEmail(user.getEmail(), "Your OTP Code", "Your OTP is: " + otp);

        return "OTP sent to email";
    }

    public boolean resetPasswordWithOtp(String email, String otp, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!otpService.validateOtp(otp, user)) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

    // ✅ Login method with roles included in JWT
    public String login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", user.getRole().getName()); // e.g., ROLE_ADMIN

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        return jwtUtil.generateToken(claims, userDetails);
    }
}
