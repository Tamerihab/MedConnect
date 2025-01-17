package com.med.MedConnect.services.observer;

public class SMSNotification implements Observer {

    private String notificationType;
    private String message;

    public SMSNotification(Subject subject) {
        subject.subscribe(this);
    }

    @Override
    public void update(String notificationType, String message, int userId) {
        this.notificationType = notificationType;
        this.message = message;
        sendSMS();
    }

    private void sendSMS() {
        System.out.println("SMS sent with notification type: " + notificationType + " and message: " + message);
    }
}
