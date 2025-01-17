package com.med.MedConnect.Model.Event;

public abstract class EventScheduler<T extends Event> {

    // Template method defining the skeleton of the scheduling process
    public final void scheduleEvent(T event) {
        validateEventDetails(event);
        assignLocation(event, getDefaultLocation()); // Assign location based on event type
        notifyParticipants(event);
        customScheduleSteps(event); // Custom scheduling steps for specific event types
        finalizeSchedule(event);
    }

    // Common steps with default implementations
    public void validateEventDetails(T event) {
        System.out.println("Validating event: " + event.getName());
    }

    public void assignLocation(T event, String location) {
        event.setDescription(event.getDescription() + " Location assigned: " + location);
    }

    public void notifyParticipants(T event) {
        System.out.println("Notifying participants for event: " + event.getName());
    }

    // Hook method for custom behavior
    protected abstract void customScheduleSteps(T event);

    // Default method to get the location; can be overridden
    protected abstract String getDefaultLocation();

    // Finalize scheduling
    public void finalizeSchedule(T event) {
        System.out.println("Finalizing scheduling for event: " + event.getName());
    }
}