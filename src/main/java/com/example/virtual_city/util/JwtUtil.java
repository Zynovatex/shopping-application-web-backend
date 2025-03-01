package com.example.virtual_city.util;


import com.example.virtual_city.service.TokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;



@Component
public class JwtUtil {
    private static final String SECRET_KEY = "your_secret_key_your_secret_key_your"; // Must be at least 32 characters for HMAC

    public JwtUtil(TokenService tokenService) {
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // Generates a secure key
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Updated method
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Updated method
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }



}
