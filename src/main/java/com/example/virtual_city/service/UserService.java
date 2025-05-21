package com.example.virtual_city.service;



import com.example.virtual_city.dto.BuyerProfileUpdateDTO;
import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUserProfile(String email, BuyerProfileUpdateDTO updateDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (updateDto.getName() != null) {
            user.setName(updateDto.getName());
        }
        if (updateDto.getEmail() != null) {
            user.setEmail(updateDto.getEmail());
        }
        if (updateDto.getProfilePictureUrl() != null) {
            user.setProfilePictureUrl(updateDto.getProfilePictureUrl());
        }

        return userRepository.save(user);
    }
}
