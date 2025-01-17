package com.med.MedConnect.services.observer;

import com.med.MedConnect.Model.Notifications.Notification;
import com.med.MedConnect.Model.Notifications.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class InAppNotification implements Observer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationRepo notificationRepository;

    public InAppNotification(Subject subject) {
        subject.subscribe(this);
    }

    @Override
    public void update(String notificationType, String message, int userId) {
        // Create and save notification to database
        Notification notification = new Notification(notificationType, message, userId);
        notificationRepository.save(notification);

        // Send real-time notification via WebSocket
        messagingTemplate.convertAndSendToUser(
                Integer.toString(userId),
                "/queue/notifications",
                notification
        );
    }
}
