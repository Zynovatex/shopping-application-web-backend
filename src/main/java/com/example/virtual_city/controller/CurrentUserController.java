//Create API to fetch user profile after login
package com.example.virtual_city.controller;


import com.example.virtual_city.dto.BuyerProfileUpdateDTO;
import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.UserRepository;
import com.example.virtual_city.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CurrentUserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public CurrentUserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public User getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
    }

    @PutMapping("/profile")
    public User updateUserProfile(@AuthenticationPrincipal UserDetails userDetails,
                                  @RequestBody BuyerProfileUpdateDTO updateDto) {
        return userService.updateUserProfile(userDetails.getUsername(), updateDto);
    }


}
