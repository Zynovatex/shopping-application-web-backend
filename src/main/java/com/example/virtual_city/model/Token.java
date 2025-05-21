package com.example.virtual_city.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean revoked;
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //Getter and Setter
    public String getToken() {
        return token;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public User getUser() {
        return user;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}

