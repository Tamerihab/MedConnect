package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.Notifications.Notification;
import com.med.MedConnect.Model.Notifications.NotificationRepo;
import com.med.MedConnect.services.observer.NotificationSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationSubject notificationSubject;
    private final NotificationRepo notificationRepo;

    @Autowired
    public NotificationController(NotificationSubject notificationSubject, NotificationRepo notificationRepo) {
        this.notificationSubject = notificationSubject;
        this.notificationRepo = notificationRepo;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody Map<String, Object> payload) {
        try {
            String type = (String) payload.get("type");
            String message = (String) payload.get("message");
            Integer userId = (Integer) payload.get("userId");

            if (type == null || message == null || userId == null) {
                return ResponseEntity.badRequest().body("Missing required fields");
            }

            // Trigger the notification through the observer pattern
            notificationSubject.notifyObservers(type, message, userId);

            return ResponseEntity.ok().body("Notification sent successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error sending notification: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable int userId) {
        try {
            List<Notification> notifications = notificationRepo.findByUserIdOrderByCreatedAtDesc(userId);
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> getNotification(@PathVariable int notificationId) {
        return notificationRepo.findById(notificationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable int notificationId) {
        try {
            if (!notificationRepo.existsById(notificationId)) {
                return ResponseEntity.notFound().build();
            }
            notificationRepo.deleteById(notificationId);
            return ResponseEntity.ok().body("Notification deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error deleting notification: " + e.getMessage());
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteAllUserNotifications(@PathVariable int userId) {
        try {
            notificationRepo.deleteByUserId(userId);
            return ResponseEntity.ok().body("All notifications deleted for user");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error deleting notifications: " + e.getMessage());
        }
    }
}