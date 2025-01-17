package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.Donation.Donation;
import com.med.MedConnect.Model.Donation.MonetaryDonation;
import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.Item.Medicine;
import com.med.MedConnect.Model.Item.Equipment;
import com.med.MedConnect.Model.Item.Condition;
import com.med.MedConnect.Model.Donation.DonationRepo;  // Import the Donation Repository
import com.med.MedConnect.Model.Donation.DonationType;
import com.med.MedConnect.Model.Item.ItemRepo;  // Import the Item Repository
import com.med.MedConnect.Model.Item.ItemType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.med.MedConnect.Model.User.User;
import com.med.MedConnect.Model.User.UserRepo;
import org.springframework.ui.Model;

import java.util.Date;

@Controller
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationRepo donationRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/add")
    public String showDonationForm(Model model, @RequestParam(required = false) String itemType) {
        // Instantiate the donation object (MonetaryDonation or ItemDonation)
        model.addAttribute("donation", new MonetaryDonation());

        // Dynamically instantiate either Medicine or Equipment based on itemType
        if ("Medicine".equals(itemType)) {
            model.addAttribute("item", new Medicine());  // Instantiate Medicine
        } else if ("Equipment".equals(itemType)) {
            model.addAttribute("item", new Equipment());  // Instantiate Equipment
        }

        return "addDonation";  // Return the form view (addDonation.html)
    }

    @PostMapping("/add")
    public String createDonation(@ModelAttribute Donation donation,
                                  @RequestParam(required = false) Double amount,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String description,
                                  @RequestParam(required = false) String itemType,
                                  @RequestParam(required = false) String manufacturer,
                                  @RequestParam(required = false) Date expiryDate,
                                  @RequestParam(required = false) int quantity,
                                  @RequestParam(required = false) Condition itemCondition) {

        // Retrieve the user from the database using the userId (hardcoded userId = 2 for now)
        int userId = 2;
        User user = userRepo.findById(userId).orElse(null);

        if (user == null) {
            // Handle the case where the user is not found
            return "redirect:/donations";  // Redirect to an error page or show a message
        }

        // Handle Monetary Donation
        if ("Monetary".equals(donation.getDonationType())) {
            MonetaryDonation monetaryDonation = new MonetaryDonation();
            monetaryDonation.setAmount(amount);  // Set the amount for monetary donation
            monetaryDonation.setDonationType(DonationType.MONETARY);
            monetaryDonation.setUser(user);  // Associate the donation with the user
            donationRepo.save(monetaryDonation);  // Save the monetary donation
        }
        // Handle Item Donation
        else if ("Item".equals(donation.getDonationType())) {
            Item item = null;
            
            // Create the appropriate Item (Medicine or Equipment) based on the selected itemType
            if ("Medicine".equals(itemType)) {
                item = new Medicine(name, description, manufacturer, expiryDate, quantity);  // Create a Medicine
                item.setType(ItemType.MEDICINE);  // Set the type to Medicine
            } else if ("Equipment".equals(itemType)) {
                item = new Equipment(name, description, itemCondition, quantity);  // Create an Equipment
                item.setType(ItemType.EQUIPMENT);  // Set the type to Equipment
            }

            // Save the item first (either Medicine or Equipment)
            itemRepo.save(item);

            // Set the Item for the donation (this will be stored in the same table as Donation)
            donation.setDonationType(DonationType.ITEM);  // Set the donation type to ITEM
            donation.setItem(item);  // Link the item to the donation
            donation.setUser(user);  // Link the user to the donation
            donationRepo.save(donation);  // Save the item donation
        }

        return "redirect:/donations";  // Redirect to donations list
    }
}
