package com.example.virtual_city.repository;

import com.example.virtual_city.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ Find User by Email (used for checking duplicates and login)
    Optional<User> findByEmail(String email);

    // ✅ (Optional Future Use) Find All Users by Status (PENDING / ACTIVE) if needed later
    // List<User> findAllByStatus(AdminStatus status);

    // ✅ (Optional Future Use) Find All Admin Users
    // List<User> findAllByRole_Name(String roleName); // Example: roleName = "ADMIN"
}
