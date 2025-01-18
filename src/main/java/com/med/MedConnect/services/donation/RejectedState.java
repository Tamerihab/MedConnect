package com.med.MedConnect.services.donation;

import com.med.MedConnect.Model.Donation.Donation;

public class RejectedState implements DonationState {
    @Override
    public void handleDonation(Donation donation) {
        System.out.println("Donation has been Rejected.");
        // Example logic: Log rejection reason or notify the user
        System.out.println("Your donation could not be processed. Please contact support.");
    }
}
