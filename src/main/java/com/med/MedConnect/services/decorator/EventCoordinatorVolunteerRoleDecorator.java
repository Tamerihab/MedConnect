package com.med.MedConnect.services.decorator;

import com.med.MedConnect.Model.volunteer.VolunteerRole;

public class EventCoordinatorVolunteerRoleDecorator extends BasicVolunteerDecorator{
    public EventCoordinatorVolunteerRoleDecorator(VolunteerRole volunteer) {
        super(volunteer);
    }

    @Override
    public void performDuties() {
        super.performDuties();
        addRole("Event Coordinator");
        addResponsibility("Plan and organize event logistics");
        addResponsibility("Manage volunteer teams");
    }
}
