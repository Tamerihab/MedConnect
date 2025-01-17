package com.med.MedConnect.Model.Volunteer;

import com.med.MedConnect.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicVolunteerRoleRepo extends JpaRepository<BasicVolunteerRole, Integer> {
    Optional<BasicVolunteerRole> findByUser(User user);
    Optional<BasicVolunteerRole> findByUserId(int userId);


}
