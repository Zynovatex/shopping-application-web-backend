package com.example.virtual_city.dto;

import com.example.virtual_city.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AdminResponseDTO {
    private Long id;
    private String adminId;
    private String name;
    private String email;
    private String photo;
    private String role;
    private String status;
    private String lastLogin;
    private LocalDateTime createdAt;  // ✅ NEW
    private List<String> categories;

    public static AdminResponseDTO fromEntity(User user) {
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(user.getId());
        dto.setAdminId(user.getAdminId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoto(user.getPhoto() != null ? user.getPhoto() : "https://i.pravatar.cc/150?u=" + user.getEmail());
        dto.setRole(user.getRole().getName());
        dto.setStatus(user.getStatus().name());
        dto.setLastLogin(user.getLastLogin());
        dto.setCreatedAt(user.getCreatedAt()); // ✅ map createdAt
        dto.setCategories(user.getAllowedModules());
        return dto;
    }
}
