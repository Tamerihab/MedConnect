package com.med.MedConnect.Model.volunteer;

import com.med.MedConnect.Model.Event.Event;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class BasicVolunteerRole implements VolunteerRole{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int volunteerID;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @Column
    private int hours;
    @ElementCollection
    @CollectionTable(name = "volunteer_skills", joinColumns = @JoinColumn(name = "volunteerid"))
    @Column(name = "skill")
    private List<String> skills;

    public BasicVolunteerRole() {
    }

    public BasicVolunteerRole(int hours, List<String> skills) {
        this.hours = hours;
        this.skills = skills;
    }

    public int getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(int volunteerID) {
        this.volunteerID = volunteerID;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "BasicVolunteerRole{" +
                "volunteerID=" + volunteerID +
                ", hours=" + hours +
                ", skills=" + skills +
                '}';
    }

    @Override
    public void performDuties() {

    }

    @Override
    public void getResponsibilities() {

    }
}
