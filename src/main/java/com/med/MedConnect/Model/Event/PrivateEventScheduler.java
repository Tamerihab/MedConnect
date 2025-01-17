package com.med.MedConnect.Model.Event;

public class PrivateEventScheduler extends EventScheduler<Event> {

    @Override
    protected void customScheduleSteps(Event event) {
        System.out.println("Arranging private security for the event: " + event.getName());
    }

    @Override
    protected String getDefaultLocation() {
        return "Private Event Hall";
    }
}