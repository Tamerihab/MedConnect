package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.Donation.Donation;
import com.med.MedConnect.Model.Donation.MonetaryDonation;
import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.Item.Medicine;
import com.med.MedConnect.Model.Item.Equipment;
import com.med.MedConnect.Model.Donation.DonationRepo;
import com.med.MedConnect.Model.Donation.DonationType;
import com.med.MedConnect.Model.Item.ItemRepo;
import com.med.MedConnect.Model.User.User;
import com.med.MedConnect.Model.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationRepo donationRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;
    public User getUserById(int userId) {
        return userRepo.findById(userId).orElse(null);
    }

    // Fetch all donations
    @GetMapping("/all")
    public List<Donation> getAllDonations() {
        return donationRepo.findAll();  // Fetch all donations from the repository
    }

    // Endpoint to handle monetary donations
    @PostMapping("/monetary")
    public MonetaryDonation saveMonetaryDonation(@RequestBody MonetaryDonation monetaryDonation) {
        // You can get the user directly using userRepo
        User user = getUserById(monetaryDonation.getUser().getId());
        if (user != null) {
            monetaryDonation.setUser(user);  // Set the user to the donation
            monetaryDonation.setDonationType(DonationType.MONETARY);  // Set donation type
            return donationRepo.save(monetaryDonation);  // Save the monetary donation
        }
        return null; // Handle case where user is not found
    }



    // Endpoint to handle medicine donations
    @PostMapping("/medicine")
    public Medicine saveMedicine(@RequestBody Medicine medicine) {
        // Get the user from the request (assuming the userId is passed in the request body)
        User user = getUserById(medicine.getUser().getId());
        if (user != null) {

            medicine.setUser(user);  // Set the user for the medicine

            // Save the Medicine item first
            Item savedMedicine = itemRepo.save(medicine);

            // Create a Donation object for the item donation
            Donation donation = new Donation();
            donation.setDonationType(DonationType.ITEM);
            donation.setItem(savedMedicine);  // Set the saved item (medicine)
            donation.setUser(user);  // Set the user to the donation

            // Save the donation record
            Donation savedDonation = donationRepo.save(donation);

            // Set the donationId in the Medicine item
            medicine.setDonation(savedDonation);  // Associate donation with the medicine

            // Save the Medicine with the donation ID
            itemRepo.save(medicine);

            // Return the saved Medicine item with its donation set
            return (Medicine) savedMedicine;
        }
        return null;  // Handle case where user is not found
    }


    // Endpoint to handle equipment donations
    @PostMapping("/equipment")
    public Equipment saveEquipment(@RequestBody Equipment equipment) {
        // Get the user from the request (assuming the userId is passed in the request body)
        User user = getUserById(equipment.getUser().getId());
        if (user != null) {

            equipment.setUser(user);  // Set the user for the equipment

            // Save the Equipment item first
            Item savedEquipment = itemRepo.save(equipment);

            // Create a Donation object for the item donation
            Donation donation = new Donation();
            donation.setDonationType(DonationType.ITEM);
            donation.setItem(savedEquipment);  // Set the saved item (equipment)
            donation.setUser(user);  // Set the user to the donation

            // Save the donation record
            Donation savedDonation = donationRepo.save(donation);

            // Set the donationId in the Equipment item
            equipment.setDonation(savedDonation);  // Associate donation with the equipment

            // Save the Equipment with the donation ID
            itemRepo.save(equipment);

            // Return the saved Equipment item with its donation set
            return  itemRepo.save(equipment);
        }
        return null;  // Handle case where user is not found
    }


}
