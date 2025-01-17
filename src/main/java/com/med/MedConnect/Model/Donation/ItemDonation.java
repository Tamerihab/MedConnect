package com.med.MedConnect.Model.Donation;

import com.med.MedConnect.Model.Item.Item;
import jakarta.persistence.*;

@Entity
public class ItemDonation extends Donation {

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;  // Reference to the Item object (either Medicine or Equipment)

    // Constructor
    public ItemDonation() {
        super();
        this.setDonationType(DonationType.ITEM);  // Set donation type to ITEM
    }

    // Getter and Setter for Item
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
