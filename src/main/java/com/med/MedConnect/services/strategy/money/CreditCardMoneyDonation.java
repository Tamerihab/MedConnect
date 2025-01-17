package com.med.MedConnect.services.strategy.money;

public class CreditCardMoneyDonation implements MoneyDonationStrategy {
    @Override
    public void pay(String donationDetails) {
        System.out.println("Processing credit card donation: " + donationDetails);
    }
}
