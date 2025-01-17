package com.med.MedConnect.services.strategy.money;

public class MoneyDonationProcessor {

    private MoneyDonationStrategy strategy;

    public void setStrategy(MoneyDonationStrategy strategy) {
        this.strategy = strategy;
    }

    public void processDonation(String donationDetails) {
        if (strategy == null) {
            System.out.println("No donation strategy selected.");
        } else {
            strategy.pay(donationDetails);
        }
    }
}
