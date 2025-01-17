package com.med.MedConnect.services.observer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationSubject implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String notificationType, String message, int userId) {
        for (Observer observer : observers) {
            observer.update(notificationType, message, userId);
        }
    }
}
