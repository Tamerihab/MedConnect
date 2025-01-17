package com.med.MedConnect.services.observer;

public enum NotificationType {
    DONATION_SUCCESS("Donation Success"),
    NEW_EVENT("New Event");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
