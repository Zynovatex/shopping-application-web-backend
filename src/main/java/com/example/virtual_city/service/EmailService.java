package com.example.virtual_city.service;

import com.example.virtual_city.model.User;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // ✅ Existing method (no change)
    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // true = html
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    // ✅ New method for sending Admin Invitation Email
    public void sendAdminInvitationEmail(User admin) {
        String subject = "VirtualCity Admin Panel - Set Your Password";

        // Temporary basic link (you can improve later to include token etc.)
        String setPasswordLink = "https://your-frontend-url.com/set-password?email=" + admin.getEmail();

        String body = "<h2>Welcome to VirtualCity Admin Panel</h2>"
                + "<p>Hello " + admin.getName() + ",</p>"
                + "<p>You have been invited to join VirtualCity Admin Panel as an Admin.</p>"
                + "<p>Please click the link below to set your password and activate your account:</p>"
                + "<a href='" + setPasswordLink + "'>Set Your Password</a>"
                + "<p>If you did not expect this email, please ignore it.</p>";

        sendEmail(admin.getEmail(), subject, body);
    }
}
