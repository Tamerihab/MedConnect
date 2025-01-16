package com.med.MedConnect.Model.Event;

import com.med.MedConnect.Model.volunteer.BasicVolunteerRole;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventID;
    @Column
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasicVolunteerRole> volunteerRoles = new ArrayList<>();

    public Event() {
    }

    public Event(long id, String Name, String description) {
        this.name = Name;
        this.description = description;
    }

    public long getId() {
        return eventID;
    }

    public void setId(long id) {
        this.eventID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BasicVolunteerRole> getVolunteerRoles() {
        return volunteerRoles;
    }

    public void setVolunteerRoles(List<BasicVolunteerRole> volunteerRoles) {
        this.volunteerRoles = volunteerRoles;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + eventID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", volunteerRoles=" + volunteerRoles +
                '}';
    }
}
