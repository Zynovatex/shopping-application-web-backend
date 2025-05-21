package com.example.virtual_city.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;

    private String password;

    @ManyToOne(fetch = FetchType.EAGER)  // ✅ Now role is a relation instead of an Enum
    @JoinColumn(name = "role_id", nullable = false)  // ✅ Stores the role_id from Role table
    private Role role;

    private boolean enabled = true;

    @Column(length = 500)
    private String profilePictureUrl; // stores image URL (e.g. Firebase)


    // Manually adding getter methods

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public Role getRole() { return role; }  // ✅ Getter for role
    public String getPassword() {
        return password;
    }

    // Manually adding setter methods
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
