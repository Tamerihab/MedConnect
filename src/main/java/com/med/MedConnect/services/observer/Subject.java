package com.med.MedConnect.services.observer;

public interface Subject {

    public void subscribe(Observer observer);
    public void unsubscribe(Observer observer);
    public void notifyObservers(String notificationType, String message, int userId);
}
