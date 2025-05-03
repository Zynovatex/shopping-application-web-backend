package com.example.virtual_city.dto;

import lombok.Data;
import java.util.List;

@Data
public class UpdateAdminRequestDTO {
    private String name;
    private String role; // e.g., "ROLE_ADMIN"
    private String status; // e.g., ACTIVE / PENDING
    private List<String> allowedModules; // same as "categories"
}
