package com.example.virtual_city.service;



import com.example.virtual_city.model.Otp;
import com.example.virtual_city.model.User;
import com.example.virtual_city.repository.OtpRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {
    private final OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public String generateOtp(User user) {
        // ✅ Generate a random 6-digit OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        // ✅ Set OTP expiration time (5 minutes)
        Otp otpEntity = new Otp();
        otpEntity.setOtp(otp);
        otpEntity.setExpirationTime(LocalDateTime.now().plusMinutes(5));
        otpEntity.setUser(user);

        otpRepository.save(otpEntity);
        return otp;
    }

    public boolean validateOtp(String otp, User user) {
        Otp storedOtp = otpRepository.findByOtp(otp)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (!storedOtp.getUser().equals(user)) {
            return false;  // OTP does not belong to user
        }

        if (storedOtp.getExpirationTime().isBefore(LocalDateTime.now())) {
            return false;  // OTP expired
        }

        otpRepository.delete(storedOtp); // ✅ Delete OTP after use
        return true;
    }
}
