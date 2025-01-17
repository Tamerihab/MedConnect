package com.med.MedConnect.Model.Notifications;

import com.med.MedConnect.Model.User.User;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationID;

    @Column
    private String type;

    @Column
    private String message;

    @Column(name = "userid")
    private int userId;  // Changed to store user ID directly

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    // Default constructor
    public Notification() {
        this.createdAt = new Date();
    }

    // Updated constructor
    public Notification(String type, String message, int userId) {
        this.type = type;
        this.message = message;
        this.userId = userId;
        this.createdAt = new Date();
    }

    // Update getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() { return notificationID; }
    public void setId(int id) { this.notificationID = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}