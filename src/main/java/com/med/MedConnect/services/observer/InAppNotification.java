package com.med.MedConnect.services.observer;

import com.med.MedConnect.Model.Notifications.Notification;
import com.med.MedConnect.Model.Notifications.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class InAppNotification implements Observer {
    private static final Logger log = LoggerFactory.getLogger(InAppNotification.class);

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepo notificationRepository;

    @Autowired
    public InAppNotification(SimpMessagingTemplate messagingTemplate,
                             NotificationRepo notificationRepository,
                             Subject subject) {
        this.messagingTemplate = messagingTemplate;
        this.notificationRepository = notificationRepository;
        subject.subscribe(this);
    }

    @Override
    public void update(String notificationType, String message, int userId) {
        try {
            // Create and save notification
            Notification notification = new Notification(notificationType, message, userId);
            notificationRepository.save(notification);

            // Send real-time notification
            messagingTemplate.convertAndSendToUser(
                    String.valueOf(userId),
                    "/queue/notifications",
                    notification
            );

        } catch (Exception e) {
            log.error("Failed to process in-app notification: " + e.getMessage(), e);
        }
    }
}
