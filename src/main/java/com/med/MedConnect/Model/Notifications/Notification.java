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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "userID")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    // Default constructor required by JPA
    public Notification() {}

    public Notification(String type, String message) {
        this.type = type;
        this.message = message;
        this.createdAt = new Date();
    }

    // Getters and setters
    public int getId() { return notificationID; }
    public void setId(int id) { this.notificationID = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}