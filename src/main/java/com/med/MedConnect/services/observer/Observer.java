package com.med.MedConnect.services.observer;

public interface Observer {

    void update(String notificationType, String message, int userId);
}
