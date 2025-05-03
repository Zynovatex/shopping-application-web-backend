// 📄 File: AdminLog.java
package com.example.virtual_city.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adminId;         // e.g., UUID or username
    private String adminName;       // Full name of the admin
    private String action;          // CREATE_ADMIN, RESET_PASSWORD, etc.
    private String targetType;      // ADMIN, SHOP, PRODUCT, etc.
    private String targetId;        // ID of the target entity (adminId, shopId, etc.)
    private String description;     // Description of the action performed

    private LocalDateTime timestamp;

    @PrePersist
    public void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}

