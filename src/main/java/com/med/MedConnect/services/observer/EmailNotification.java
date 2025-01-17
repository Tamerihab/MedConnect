package com.med.MedConnect.services.observer;

import com.med.MedConnect.Model.User.User;
import com.med.MedConnect.Model.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailNotification implements Observer {

    private final JavaMailSender emailSender;
    private final UserRepo userRepo;

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
        Optional<User> optionalUser = userRepo.findById(userId);
        optionalUser.ifPresent(user -> {
            if (user.getEmail() != null) {
                sendEmail(user.getEmail(), notificationType, message);
            }
        });
    }

    private void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        emailSender.send(mailMessage);
    }
}
