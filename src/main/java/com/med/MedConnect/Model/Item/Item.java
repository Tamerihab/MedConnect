package com.med.MedConnect.Model.Item;

import jakarta.persistence.*;

import java.util.Iterator;

import com.med.MedConnect.Model.Donation.Donation;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements ItemComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column
    private ItemType type;

    public Item() {
    }

    @ManyToOne
    @JoinColumn(name = "donation_id")  // Foreign key to Donation table
    private Donation donation;  

    public Item(String name, String description, ItemType type, int quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.type = type;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getQuantity() {
        return quantity;
    }

    public ItemType getType() {
        return type;
    }
    public void setType(ItemType type) {
        this.type = type;
    }

    public void addChild(ItemComponent itemComponent) {
        throw new UnsupportedOperationException("This operation is not supported for Leaf objects");
    }

    public void removeChild(ItemComponent itemComponent) {
        throw new UnsupportedOperationException("This operation is not supported for Leaf objects");
    }

    @Override
    public ItemComponent getChild(int i) {
        throw new UnsupportedOperationException("This operation is not supported for Leaf objects");
    }

    public Iterator<ItemComponent> createIterator() {
        return new NullIterator();
    }

    @Override
    public abstract void getDetails();
}
