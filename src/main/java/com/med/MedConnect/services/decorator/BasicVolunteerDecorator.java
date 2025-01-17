package com.med.MedConnect.services.decorator;

import com.med.MedConnect.Model.Volunteer.VolunteerRole;

import java.util.List;

public abstract class BasicVolunteerDecorator implements VolunteerRole {
    protected final VolunteerRole volunteer;

    public BasicVolunteerDecorator(VolunteerRole volunteer) {
        this.volunteer = volunteer;
    }

    @Override
    public void performDuties() {
        volunteer.performDuties();
    }

    @Override
    public List<String> getRoles() {
        return volunteer.getRoles();
    }

    @Override
    public List<String> getResponsibilities() {
        return volunteer.getResponsibilities();
    }

    @Override
    public void addRole(String role) {
        volunteer.addRole(role);
    }

    @Override
    public void addResponsibility(String responsibility) {
        volunteer.addResponsibility(responsibility);
    }
}
