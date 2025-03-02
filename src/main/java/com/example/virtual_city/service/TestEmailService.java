package com.example.virtual_city.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class TestEmailService implements CommandLineRunner {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void run(String... args) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("receiver@example.com");  // Replace with test recipient email
            message.setSubject("Test Email");
            message.setText("This is a test email from Spring Boot.");
            mailSender.send(message);
            System.out.println("Test email sent successfully!");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
