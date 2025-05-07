package com.example.virtual_city.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id", unique = true)
    private String adminId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name = "last_login")
    private String lastLogin;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // auto-set on insert

    @Column(name = "photo")
    private String photo; // base64 or image URL

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(name = "is_super_admin", nullable = false)
    private boolean isSuperAdmin = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AdminStatus status = AdminStatus.PENDING;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_allowed_modules", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "module")
    private List<String> allowedModules;
}
