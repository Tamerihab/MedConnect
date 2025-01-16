package com.med.MedConnect.services.observer;

public class InAppNotification implements Observer {

    private String notificationType;
    private String message;

    public InAppNotification(Subject subject) {
        subject.subscribe(this);
    }

    @Override
    public void update(String notificationType, String message) {
        this.notificationType = notificationType;
        this.message = message;
        sendInAppNotification();
    }

    private void sendInAppNotification() {
        System.out.println("In-app notification sent with notification type: " + notificationType + " and message: " + message);
    }
}
