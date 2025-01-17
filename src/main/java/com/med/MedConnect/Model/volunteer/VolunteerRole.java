package com.med.MedConnect.Model.volunteer;

import java.util.List;

public interface VolunteerRole {

    void performDuties();
    List<String> getRoles();
    List<String> getResponsibilities();
    void addRole(String role);
    void addResponsibility(String responsibility);
}
