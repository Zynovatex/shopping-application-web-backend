package com.example.virtual_city.controller;

import com.example.virtual_city.dto.*;
import com.example.virtual_city.model.AdminStatus;
import com.example.virtual_city.model.Role;
import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.RoleRepository;
import com.example.virtual_city.repository.UserRepository;
import com.example.virtual_city.service.AdminOverviewService;
import com.example.virtual_city.service.AdminService;
import com.example.virtual_city.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminOverviewService adminOverviewService;
    private final AdminService adminService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private static final boolean USE_MOCK = false;

    @GetMapping("/overview")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Map<String, Object>> getAdminOverview() {
        if (USE_MOCK) {
            Map<String, Object> mockResponse = new HashMap<>();
            mockResponse.put("stats", List.of());
            mockResponse.put("pageAccessBreakdown", List.of());
            mockResponse.put("activityTrend", List.of());
            mockResponse.put("rolesByActivity", List.of());
            return ResponseEntity.ok(mockResponse);
        } else {
            return ResponseEntity.ok(adminOverviewService.getAdminOverview());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<String> createAdmin(@RequestBody CreateAdminRequest request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Admin already exists");
        }

        Role role = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Admin role not found"));

        User newAdmin = new User();
        newAdmin.setEmail(request.getEmail());
        newAdmin.setName(request.getName());
        newAdmin.setPassword("TEMPORARY");
        newAdmin.setRole(role);
        newAdmin.setSuperAdmin(false);
        newAdmin.setStatus(AdminStatus.PENDING);
        newAdmin.setAllowedModules(request.getAllowedModules());
        newAdmin.setCreatedAt(LocalDateTime.now());

        userRepository.save(newAdmin);
        emailService.sendAdminInvitationEmail(newAdmin);

        return ResponseEntity.ok("Admin created and invitation sent.");
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<AdminResponseDTO>> getAllAdmins(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String category
    ) {
        List<AdminResponseDTO> admins = adminService.getAllAdmins(search, role, status, category);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/admins/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<AdminResponseDTO> getAdminById(@PathVariable Long id) {
        AdminResponseDTO admin = adminService.getAdminById(id);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/admins/{id}/reset-password")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<String> resetAdminPassword(
            @PathVariable Long id,
            @RequestBody ResetPasswordRequest request
    ) {
        String superAdminEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> superAdminOpt = userRepository.findByEmail(superAdminEmail);
        if (superAdminOpt.isEmpty()) {
            return ResponseEntity.status(403).body("Super admin not found.");
        }

        User superAdmin = superAdminOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), superAdmin.getPassword())) {
            return ResponseEntity.status(403).body("Invalid super admin password.");
        }

        User targetAdmin = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found."));

        String newTempPassword = "Temp@1234";
        targetAdmin.setPassword(passwordEncoder.encode(newTempPassword));
        targetAdmin.setStatus(AdminStatus.PENDING);
        userRepository.save(targetAdmin);

        emailService.sendAdminResetPasswordEmail(targetAdmin);

        return ResponseEntity.ok("Reset password email sent successfully.");
    }

    @PutMapping("/admins/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<String> updateAdminDetails(
            @PathVariable Long id,
            @RequestBody UpdateAdminRequestDTO dto
    ) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Admin not found.");
        }

        User admin = userOpt.get();

        if (dto.getName() != null) admin.setName(dto.getName());
        if (dto.getStatus() != null) admin.setStatus(AdminStatus.valueOf(dto.getStatus()));
        if (dto.getPhoto() != null) admin.setPhoto(dto.getPhoto());
        if (dto.getAllowedModules() != null) admin.setAllowedModules(dto.getAllowedModules());

        if (dto.getPassword() != null && dto.getOldPassword() != null) {
            if (!passwordEncoder.matches(dto.getOldPassword(), admin.getPassword())) {
                return ResponseEntity.status(403).body("Incorrect current password");
            }
            admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        userRepository.save(admin);
        return ResponseEntity.ok("Admin details updated successfully.");
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<AdminResponseDTO> getCurrentAdminInfo() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(AdminResponseDTO.fromEntity(userOpt.get()));
    }
}
