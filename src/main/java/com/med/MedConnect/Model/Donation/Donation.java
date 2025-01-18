package com.med.MedConnect.Model.Donation;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.User.User;
import com.med.MedConnect.services.donation.ApprovedState;
import com.med.MedConnect.services.donation.DonationState;
import com.med.MedConnect.services.donation.PendingState;
import com.med.MedConnect.services.donation.RejectedState;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "donation_type", discriminatorType = DiscriminatorType.STRING)
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;

    // Field to store the type of donation (Monetary or Item)
    @Enumerated(EnumType.STRING)
    @Column(name = "donation_type")
    private DonationType donationType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "donation_status")
    @Enumerated(EnumType.STRING)
    private DonationStateEnum donationStatus;

    @Transient
    private DonationState currentState;

    // Constructor
    public Donation() {
        this.donationStatus = DonationStateEnum.PENDING; // Default state
        this.currentState = new PendingState();
    }

    // Getters and Setters
    public int getId() {
        return donationId;
    }

    public void setId(int donationId) {
        this.donationId = donationId;
    }

    public DonationType getDonationType() {
        return donationType;
    }

    // Setter for donationType
    public void setDonationType(DonationType donationType) {
        this.donationType = donationType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public DonationState getCurrentState() {
        if (currentState == null) {
            this.currentState = mapStateToClass(this.donationStatus);
        }
        return currentState;
    }
    // Function to set the state dynamically
    public void setDonationState(DonationStateEnum newState) {
        this.donationStatus = newState; // Update the enum representing the state
        this.currentState = mapStateToClass(newState); // Map the new enum to the appropriate state class
    }

    public void handle() {
        getCurrentState().handleDonation(this);
    }

    private DonationState mapStateToClass(DonationStateEnum stateEnum) {
        switch (stateEnum) {
            case PENDING:
                return new PendingState();
            case APPROVED:
                return new ApprovedState();
            case REJECTED:
                return new RejectedState();
            default:
                throw new IllegalArgumentException("Unknown state: " + stateEnum);
        }
    }
}
