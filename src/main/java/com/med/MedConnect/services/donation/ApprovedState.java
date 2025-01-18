package com.med.MedConnect.services.donation;

import com.med.MedConnect.Model.Donation.Donation;

public class ApprovedState implements DonationState {
    @Override
    public void handleDonation(Donation donation) {
        System.out.println("Donation is now Approved.");
        // Example logic: Once approved, no further transitions are allowed
        // Maybe some notification or database log here
        // Example: Notify the donor
        System.out.println("Thank you for your donation! It has been approved.");
    }
}
