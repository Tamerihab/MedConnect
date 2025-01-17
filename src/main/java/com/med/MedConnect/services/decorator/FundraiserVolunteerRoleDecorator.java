package com.med.MedConnect.services.decorator;

import com.med.MedConnect.Model.Volunteer.VolunteerRole;

public class FundraiserVolunteerRoleDecorator extends BasicVolunteerDecorator {

    public FundraiserVolunteerRoleDecorator(VolunteerRole volunteer) {
        super(volunteer);
    }

    @Override
    public void performDuties() {
        super.performDuties();
        addRole("Fundraiser");
        addResponsibility("Organize fundraising campaigns");
        addResponsibility("Manage donor relations");
    }
}