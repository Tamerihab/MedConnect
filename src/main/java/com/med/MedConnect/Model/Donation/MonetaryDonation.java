package com.med.MedConnect.Model.Donation;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Monetary")  // Discriminates this type of donation
public class MonetaryDonation extends Donation {

    @Column
    private double amount;  // The amount donated

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
