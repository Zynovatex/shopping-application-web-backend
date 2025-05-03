package com.example.virtual_city.dto;

import com.example.virtual_city.model.User;
import lombok.Data;

import java.util.List;

@Data
public class AdminResponseDTO {
    private Long id;
    private String adminId;
    private String name;
    private String email;
    private String photo; // You can replace with profile image field if you add it later
    private String role;
    private String status;
    private String lastLogin;
    private List<String> categories;

    public static AdminResponseDTO fromEntity(User user) {
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(user.getId());
        dto.setAdminId(user.getAdminId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoto("https://i.pravatar.cc/150?u=" + user.getEmail()); // Placeholder image
        dto.setRole(user.getRole().getName());
        dto.setStatus(user.getStatus().name());
        dto.setLastLogin(user.getLastLogin());
        dto.setCategories(user.getAllowedModules());
        return dto;
    }
}
