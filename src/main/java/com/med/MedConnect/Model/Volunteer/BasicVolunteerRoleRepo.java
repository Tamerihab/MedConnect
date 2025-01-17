package com.med.MedConnect.Model.volunteer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicVolunteerRoleRepo extends JpaRepository<BasicVolunteerRole, Integer> {
}
