package com.med.MedConnect.services.donation;

import com.med.MedConnect.Model.Donation.Donation;
import com.med.MedConnect.Model.Donation.DonationStateEnum;
import com.med.MedConnect.Model.Donation.DonationType;
public class PendingState implements DonationState {
    @Override
    public void handleDonation(Donation donation) {
        System.out.println("Donation is currently in Pending State.");

        // Example logic: Approve if donation amount > 100, otherwise reject
        if (donation.getDonationType() == DonationType.MONETARY && donation.getItem() == null) {
            if (donation.getUser().getId() % 2 == 0) { // Example condition for simplicity
                System.out.println("Donation approved for monetary contribution.");
                donation.setDonationState(DonationStateEnum.APPROVED);
            } else {
                System.out.println("Donation rejected due to user conditions.");
                donation.setDonationState(DonationStateEnum.REJECTED);
            }
        } else if (donation.getDonationType() == DonationType.ITEM && donation.getItem() != null) {
            System.out.println("Item donation is under review. Automatically approving...");
            donation.setDonationState(DonationStateEnum.APPROVED);
        } else {
            System.out.println("Donation rejected due to incomplete details.");
            donation.setDonationState(DonationStateEnum.REJECTED);
        }
    }
}

