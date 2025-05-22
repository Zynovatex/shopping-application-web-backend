package com.example.virtual_city;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "#@Lingaa198"; // <-- change this to your desired password
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Hashed password:");
        System.out.println(encodedPassword);
    }
}
