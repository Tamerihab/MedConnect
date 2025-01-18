package com.med.MedConnect.services.donation;

import com.med.MedConnect.Model.Donation.Donation;

public interface DonationState {
    public void handleDonation(Donation donation);
}
