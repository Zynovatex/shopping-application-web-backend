package com.example.virtual_city.repository;

import com.example.virtual_city.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByOtp(String otp);
    void deleteByUserId(Long userId);
}
