package com.med.MedConnect.Model.Event;

import com.med.MedConnect.Model.volunteer.BasicVolunteerRole;
import jakarta.persistence.*;

@Entity
public class EventDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventDetailsID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "eventid")
    private Event event;

    @ManyToOne(cascade = CascadeType.ALL) //each role belongs to one volunteer
    @JoinColumn(name = "volunteer_id", referencedColumnName = "volunteerid")
    private BasicVolunteerRole volunteerRole;

    @Column
    private String attendance;

    @Column
    private int totalHours;

    public EventDetails() {
    }

    public EventDetails(int eventDetailsID, Event event, BasicVolunteerRole volunteerRole, String attendance, int totalHours) {
        this.eventDetailsID = eventDetailsID;
        this.event = event;
        this.volunteerRole = volunteerRole;
        this.attendance = attendance;
        this.totalHours = totalHours;
    }

    public int getEventDetailsID() {
        return eventDetailsID;
    }

    public void setEventDetailsID(int eventDetailsID) {
        this.eventDetailsID = eventDetailsID;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public BasicVolunteerRole getVolunteerRole() {
        return volunteerRole;
    }

    public void setVolunteerRole(BasicVolunteerRole volunteerRole) {
        this.volunteerRole = volunteerRole;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    @Override
    public String toString() {
        return "EventDetails{" +
                "eventDetailsID=" + eventDetailsID +
                ", event=" + event +
                ", volunteerRole=" + volunteerRole +
                ", attendance='" + attendance + '\'' +
                ", totalHours=" + totalHours +
                '}';
    }
}
