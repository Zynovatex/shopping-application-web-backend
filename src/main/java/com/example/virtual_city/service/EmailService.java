package com.example.virtual_city.service;

import com.example.virtual_city.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
            logger.info("Email sent to {}", to);
        } catch (MessagingException e) {
            logger.error("Failed to send email to {}: {}", to, e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendAdminInvitationEmail(User admin) {
        if (admin.getEmail() == null || admin.getName() == null) {
            throw new IllegalArgumentException("Admin email or name is null");
        }

        String subject = "VirtualCity Admin Panel - Set Your Password";
        String setPasswordLink = "http://localhost:3000/set-password?email=" + admin.getEmail();

        String body =
                "<html>" +
                        "<body>" +
                        "<h2 style='color:#4B0082;'>Welcome to VirtualCity Admin Panel</h2>" +
                        "<p>Hello <strong>" + admin.getName() + "</strong>,</p>" +
                        "<p>You have been invited to join as an Admin.</p>" +
                        "<p>Please click the button below to set your password:</p>" +
                        "<a href='" + setPasswordLink + "' target='_blank' " +
                        "style='display:inline-block;padding:10px 20px;margin-top:10px;" +
                        "background-color:#5A31F5;color:#fff;border-radius:5px;text-decoration:none;font-weight:bold;'>Set Password</a>" +
                        "<p style='margin-top:20px;'>If you did not expect this email, please ignore it.</p>" +
                        "</body>" +
                        "</html>";

        sendEmail(admin.getEmail(), subject, body);
    }

    public void sendAdminResetPasswordEmail(User admin) {
        if (admin.getEmail() == null || admin.getName() == null) {
            throw new IllegalArgumentException("Admin email or name is null");
        }

        String subject = "VirtualCity Admin Panel - Reset Your Password";
        String resetLink = "http://localhost:3000/set-password?email=" + admin.getEmail();

        String body =
                "<html>" +
                        "<body>" +
                        "<h2 style='color:#4B0082;'>Password Reset</h2>" +
                        "<p>Hello <strong>" + admin.getName() + "</strong>,</p>" +
                        "<p>Your Super Admin has triggered a password reset for your account.</p>" +
                        "<p>Please click the button below to reset your password:</p>" +
                        "<a href='" + resetLink + "' target='_blank' " +
                        "style='display:inline-block;padding:10px 20px;margin-top:10px;" +
                        "background-color:#5A31F5;color:#fff;border-radius:5px;text-decoration:none;font-weight:bold;'>Reset Password</a>" +
                        "<p style='margin-top:20px;'>If you did not expect this email, you may ignore it.</p>" +
                        "</body>" +
                        "</html>";

        sendEmail(admin.getEmail(), subject, body);
    }
}
