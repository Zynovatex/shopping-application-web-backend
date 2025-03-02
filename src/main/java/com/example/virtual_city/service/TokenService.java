package com.example.virtual_city.service;

import com.example.virtual_city.model.Token;
import com.example.virtual_city.repository.TokenRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void revokeToken(String token) {
        tokenRepository.findByToken(token).ifPresent(storedToken -> {
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        });
    }
}
