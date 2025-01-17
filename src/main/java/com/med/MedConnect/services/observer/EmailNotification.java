package com.med.MedConnect.services.observer;

import com.med.MedConnect.Model.User.User;
import com.med.MedConnect.Model.User.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailNotification implements Observer {
    private final JavaMailSender emailSender;
    private final UserRepo userRepo;

    private static final Logger log = LoggerFactory.getLogger(EmailNotification.class);

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    public EmailNotification(JavaMailSender emailSender,
                             UserRepo userRepo,
                             Subject subject) {
        this.emailSender = emailSender;
        this.userRepo = userRepo;
        subject.subscribe(this);
    }

    @Override
    public void update(String notificationType, String message, int userId) {
        try {
            Optional<User> optionalUser = userRepo.findById(userId);
            optionalUser.ifPresent(user -> {
                if (user.getEmail() != null) {
                    String emailContent = buildEmailContent(notificationType, message, user.getFirstName());
                    sendEmail(user.getEmail(), notificationType, emailContent);
                }
            });
        } catch (Exception e) {
            log.error("Failed to send email notification: " + e.getMessage(), e);
        }
    }

    private String buildEmailContent(String notificationType, String message, String userName) {
        if (notificationType.equals(NotificationType.DONATION_SUCCESS.getValue())) {
            return String.format("""
                Dear %s,
                
                Thank you for your donation! %s
                
                Your contribution makes a difference.
                
                Best regards,
                MedConnect Team
                """, userName, message);
        } else if (notificationType.equals(NotificationType.NEW_EVENT.getValue())) {
            return String.format("""
                Dear %s,
                
                We're excited to announce a new event! %s
                
                Don't miss out on this opportunity to make a difference.
                
                Best regards,
                MedConnect Team
                """, userName, message);
        }
        return message;
    }

    private void sendEmail(String to, String subject, String content) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
            emailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("Failed to send email: " + e.getMessage(), e);
        }
    }
}
