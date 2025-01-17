package com.med.MedConnect.Model.Donation;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.User.User;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "donation_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Make sure to use the column name that matches the DB column
    @Column(name = "donation_type")  // Ensure that donation_type is mapped correctly
    private String donationType;  // "Monetary" or "Item"

    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign key reference to the User table
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")  // Foreign key reference to the Item table (for Item donations)
    private Item item;  // The item for item donations

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
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
