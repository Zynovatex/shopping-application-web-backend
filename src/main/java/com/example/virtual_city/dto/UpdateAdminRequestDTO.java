package com.example.virtual_city.dto;

import lombok.Data;
import java.util.List;

@Data
public class UpdateAdminRequestDTO {
    private String name;
    private String role; // e.g., "ROLE_ADMIN"
    private String status; // ACTIVE / PENDING / DISABLED
    private List<String> allowedModules; // same as "categories"

    // ✅ NEW fields for profile update
    private String photo;          // base64 or URL
    private String oldPassword;    // for password change verification
    private String password;       // new password
}
