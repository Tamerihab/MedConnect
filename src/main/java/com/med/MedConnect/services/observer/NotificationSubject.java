package com.med.MedConnect.services.observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String notificationType, String message) {
        for (Observer observer : observers) {
            observer.update(notificationType, message);
        }
    }
}
