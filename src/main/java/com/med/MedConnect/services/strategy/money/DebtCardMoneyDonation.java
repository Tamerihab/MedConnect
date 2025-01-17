package com.med.MedConnect.services.strategy.money;

public class DebtCardMoneyDonation implements MoneyDonationStrategy {
    @Override
    public void pay(String donationDetails) {
        System.out.println("Processing debit card donation: " + donationDetails);
    }
}
