package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.User.User;
import com.med.MedConnect.Model.Volunteer.BasicVolunteerRole;
import com.med.MedConnect.Model.User.UserRepo;
import com.med.MedConnect.Model.Volunteer.BasicVolunteerRoleRepo;
import com.med.MedConnect.Model.Volunteer.VolunteerRole;
import com.med.MedConnect.services.decorator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    private BasicVolunteerRoleRepo volunteerRepository;

    @Autowired
    private UserRepo userRepository;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addVolunteerRole(
            @PathVariable int userId,
            @RequestBody BasicVolunteerRole newVolunteerRole,
            @RequestParam(required = false) List<String> specializations) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.isVolunteer()) {
                Optional<BasicVolunteerRole> volunteerOptional = volunteerRepository.findByUserId(userId);

                if (volunteerOptional.isPresent()) {
                    return ResponseEntity.badRequest().body("User is already a volunteer and their data exists.");
                }

                // Start with basic volunteer role
                VolunteerRole decoratedVolunteer = newVolunteerRole;

                // Apply decorators based on specializations
                if (specializations != null) {
                    for (String specialization : specializations) {
                        switch (specialization.toLowerCase()) {
                            case "emergency":
                                decoratedVolunteer = new EmergencyResponderVolunteerRoleDecorator(decoratedVolunteer);
                                break;
                            case "coordinator":
                                decoratedVolunteer = new EventCoordinatorVolunteerRoleDecorator(decoratedVolunteer);
                                break;
                            case "fundraiser":
                                decoratedVolunteer = new FundraiserVolunteerRoleDecorator(decoratedVolunteer);
                                break;
                        }
                    }
                }

                // Perform duties for the decorated volunteer
                decoratedVolunteer.performDuties();

                // Copy the decorated roles and responsibilities back to the persistent entity
                newVolunteerRole.setRoles(decoratedVolunteer.getRoles());
                newVolunteerRole.setResponsibilities(decoratedVolunteer.getResponsibilities());
                newVolunteerRole.setUser(user);

                // Save the volunteer role with all decorations
                volunteerRepository.save(newVolunteerRole);

                return ResponseEntity.ok("Volunteer role added with specializations: " + specializations);
            } else {
                return ResponseEntity.badRequest().body("This user isn't a volunteer. Please become a volunteer first.");
            }
        }
        return ResponseEntity.status(404).body("User not found.");
    }

    @PutMapping("/update/{userId}/specializations")
    public ResponseEntity<?> updateVolunteerSpecializations(
            @PathVariable int userId,
            @RequestParam List<String> specializations) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<BasicVolunteerRole> volunteerOptional = volunteerRepository.findByUserId(userId);

            if (volunteerOptional.isPresent()) {
                BasicVolunteerRole volunteerRole = volunteerOptional.get();

                // Create a new decorated volunteer starting with the basic role
                VolunteerRole decoratedVolunteer = volunteerRole;

                // Apply new specializations
                for (String specialization : specializations) {
                    switch (specialization.toLowerCase()) {
                        case "emergency":
                            decoratedVolunteer = new EmergencyResponderVolunteerRoleDecorator(decoratedVolunteer);
                            break;
                        case "coordinator":
                            decoratedVolunteer = new EventCoordinatorVolunteerRoleDecorator(decoratedVolunteer);
                            break;
                        case "fundraiser":
                            decoratedVolunteer = new FundraiserVolunteerRoleDecorator(decoratedVolunteer);
                            break;
                    }
                }

                // Perform duties for all decorators
                decoratedVolunteer.performDuties();

                // Update the persistent entity with new roles and responsibilities
                volunteerRole.setRoles(decoratedVolunteer.getRoles());
                volunteerRole.setResponsibilities(decoratedVolunteer.getResponsibilities());

                volunteerRepository.save(volunteerRole);

                return ResponseEntity.ok("Volunteer specializations updated successfully");
            }
            return ResponseEntity.badRequest().body("Volunteer role not found for this user");
        }
        return ResponseEntity.status(404).body("User not found");
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<?> removeVolunteerRole(@PathVariable int userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.isVolunteer()) {
                Optional<BasicVolunteerRole> volunteerOptional = volunteerRepository.findByUser(user);
                if (volunteerOptional.isPresent()) {
                    volunteerRepository.delete(volunteerOptional.get());
                }

                user.setVolunteer(false);
                userRepository.save(user);

                return ResponseEntity.ok("Volunteer role removed and user updated.");
            }
            return ResponseEntity.badRequest().body("User is not currently a volunteer.");
        }
        return ResponseEntity.status(404).body("User not found.");
    }
}