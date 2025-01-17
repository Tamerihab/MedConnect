package com.med.MedConnect.Model.Event;

public class RecurringEventScheduler extends EventScheduler<Event> {

    @Override
    protected void customScheduleSteps(Event event) {
        System.out.println("Setting up recurring schedule for the event: " + event.getName());
    }

    @Override
    protected String getDefaultLocation() {
        return "Recurring Event Location";
    }
}