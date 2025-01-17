package com.med.MedConnect.services.decorator;

import com.med.MedConnect.Model.Volunteer.VolunteerRole;

public class EmergencyResponderVolunteerRoleDecorator extends BasicVolunteerDecorator {

    public EmergencyResponderVolunteerRoleDecorator(VolunteerRole volunteer) {
        super(volunteer);
    }

    @Override
    public void performDuties() {
        super.performDuties();
        addRole("Emergency Responder");
        addResponsibility("Provide emergency medical care");
        addResponsibility("Coordinate with emergency services");
    }
}
