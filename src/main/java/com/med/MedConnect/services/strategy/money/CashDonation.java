package com.med.MedConnect.services.strategy.money;

public class CashDonation implements MoneyDonationStrategy {

    @Override
    public void pay(String donationDetails) {
        System.out.println("Processing cash donation: " + donationDetails);
    }
}
