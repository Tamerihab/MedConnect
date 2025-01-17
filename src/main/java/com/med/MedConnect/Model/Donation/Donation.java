package com.med.MedConnect.Model.Donation;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.User.User;
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

    // Constructor
    public Donation() {
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
}
