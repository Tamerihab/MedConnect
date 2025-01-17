package com.med.MedConnect.Model.Donation;



import jakarta.persistence.Entity;

@Entity
public class MonetaryDonation extends Donation {

    private Double amount;

    // Constructor
    public MonetaryDonation() {
        super();  // Call the constructor of the parent class (Donation)
        this.setDonationType(DonationType.MONETARY);  // Set the donation type to MONETARY
    }

    // Getter and setter for amount
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
