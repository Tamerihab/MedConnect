package com.med.MedConnect.services.observer;

public class EmailNotification implements Observer {

    private String notificationType;
    private String message;

    public EmailNotification(Subject subject) {
        subject.subscribe(this);
    }

    @Override
    public void update(String notificationType, String message) {
        this.notificationType = notificationType;
        this.message = message;
        sendEmail();
    }

    private void sendEmail() {
        System.out.println("Email sent with notification type: " + notificationType + " and message: " + message);
    }
}
