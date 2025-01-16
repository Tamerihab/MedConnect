package com.med.MedConnect.services.strategy.money;

public class PayPalMoneyDonation implements MoneyDonationStrategy {

    @Override
    public void pay(String donationDetails) {
        System.out.println("Processing PayPal donation: " + donationDetails);
    }
}
