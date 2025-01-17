package com.med.MedConnect.Model.Volunteer;

import java.util.List;

public interface VolunteerRole {

    void performDuties();
    List<String> getRoles();
    List<String> getResponsibilities();
    void addRole(String role);
    void addResponsibility(String responsibility);
}
