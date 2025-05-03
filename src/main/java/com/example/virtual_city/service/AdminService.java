package com.example.virtual_city.service;

import com.example.virtual_city.dto.AdminResponseDTO;
import com.example.virtual_city.dto.UpdateAdminRequestDTO;
import com.example.virtual_city.model.AdminStatus;
import com.example.virtual_city.model.Role;
import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.RoleRepository;
import com.example.virtual_city.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthService authService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public List<AdminResponseDTO> getAllAdmins(String search, String role, String status, String category) {
        List<User> users = userRepository.findAll();

        return users.stream()
                .filter(user -> user.getRole().getName().equals("ROLE_ADMIN"))
                .filter(user -> {
                    if (search == null || search.isBlank()) return true;
                    String s = search.toLowerCase();
                    return user.getName().toLowerCase().contains(s)
                            || user.getEmail().toLowerCase().contains(s)
                            || (user.getAdminId() != null && user.getAdminId().toLowerCase().contains(s));
                })
                .filter(user -> role == null || user.getRole().getName().equalsIgnoreCase(role))
                .filter(user -> status == null || user.getStatus().name().equalsIgnoreCase(status))
                .filter(user -> {
                    if (category == null || category.isBlank()) return true;
                    return user.getAllowedModules().stream().anyMatch(cat -> cat.equalsIgnoreCase(category));
                })
                .map(AdminResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public AdminResponseDTO getAdminById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));

        if (!user.getRole().getName().equals("ROLE_ADMIN")) {
            throw new RuntimeException("User is not an admin");
        }

        return AdminResponseDTO.fromEntity(user);
    }

    public void resetAdminPassword(Long adminId, String superAdminEmail, String superAdminPassword) {
        boolean isValid = authService.validateSuperAdminPassword(superAdminEmail, superAdminPassword);
        if (!isValid) {
            throw new RuntimeException("Super Admin password is incorrect.");
        }

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + adminId));

        if (!admin.getRole().getName().equals("ROLE_ADMIN")) {
            throw new RuntimeException("Target user is not an admin.");
        }

        String tempPassword = "Temp@1234";
        admin.setPassword(passwordEncoder.encode(tempPassword));
        admin.setStatus(AdminStatus.PENDING);
        userRepository.save(admin);

        emailService.sendAdminResetPasswordEmail(admin);
    }

    public void updateAdmin(Long adminId, UpdateAdminRequestDTO dto) {
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + adminId));

        if (!admin.getRole().getName().equals("ROLE_ADMIN")) {
            throw new RuntimeException("Target user is not an admin");
        }

        if (dto.getName() != null) {
            admin.setName(dto.getName());
        }

        if (dto.getStatus() != null) {
            admin.setStatus(AdminStatus.valueOf(dto.getStatus().toUpperCase()));
        }

        if (dto.getRole() != null) {
            Role role = roleRepository.findByName(dto.getRole())
                    .orElseThrow(() -> new RuntimeException("Invalid role: " + dto.getRole()));
            admin.setRole(role);
        }

        if (dto.getAllowedModules() != null) {
            admin.setAllowedModules(dto.getAllowedModules());
        }

        userRepository.save(admin);
    }
}
