package com.example.virtual_city.service;

import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Debug prints to help troubleshoot
        System.out.println("Loading user: " + user.getEmail());
        System.out.println("Password hash: " + user.getPassword());  // Should be bcrypt hash
        System.out.println("Role: " + (user.getRole() != null ? user.getRole().getName() : "No role"));

        return new UserDetailsImpl(user);
    }

    // Optional: If you want to generate authorities here (not required since UserDetailsImpl handles it)
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        if (user.getRole() == null) {
            return null;
        }
        String roleName = user.getRole().getName();
        if (!roleName.startsWith("ROLE_")) {
            roleName = "ROLE_" + roleName;
        }
        return
                java.util.Collections.singletonList(new SimpleGrantedAuthority(roleName));
    }
}
