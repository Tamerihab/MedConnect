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

    @ElementCollection
    @CollectionTable(name = "volunteer_roles", joinColumns = @JoinColumn(name = "volunteerid"))
    @Column(name = "role")
    private List<String> roles;

    @ElementCollection
    @CollectionTable(name = "volunteer_responsibilities", joinColumns = @JoinColumn(name = "volunteerid"))
    @Column(name = "responsibility")
    private List<String> responsibilities;

    public BasicVolunteerRole() {
    }

    public BasicVolunteerRole(int hours, List<String> skills, List<String> roles, List<String> responsibilities) {
        this.hours = hours;
        this.skills = skills;
        this.roles = roles;
        this.responsibilities = responsibilities;
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

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    @Override
    public String toString() {
        return "BasicVolunteerRole{" +
                "volunteerID=" + volunteerID +
                ", hours=" + hours +
                ", skills=" + skills +
                ", roles=" + roles +
                ", responsibilities=" + responsibilities +
                '}';
    }

    @Override
    public void performDuties() {
        addRole("Basic Volunteer");
        addResponsibility("General support");
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    @Override
    public List<String> getResponsibilities() {
        return responsibilities;
    }

    @Override
    public void addRole(String role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    @Override
    public void addResponsibility(String responsibility) {
        if (!responsibilities.contains(responsibility)) {
            responsibilities.add(responsibility);
        }
    }
}
