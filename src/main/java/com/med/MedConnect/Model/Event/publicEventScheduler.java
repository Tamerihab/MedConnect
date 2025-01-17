package com.med.MedConnect.Model.Event;

public class publicEventScheduler extends EventScheduler<Event> {

    @Override
    protected void customScheduleSteps(Event event) {
        System.out.println("Setting up public resources for the event: " + event.getName());
    }

    @Override
    protected String getDefaultLocation() {
        return "Public Event Venue";
    }
}