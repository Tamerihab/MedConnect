package com.med.MedConnect.Model.Item;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity // Mark as a database entity
@Table(name = "items") // Table name in the database
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    private int itemID;

    @Column(name = "name", nullable = false) // Map to a column with a non-null constraint
    private String name;

    @Column(name = "description", length = 500) // Map to a column with a max length
    private String description;

    @Column(name = "quantity", nullable = false) // Map to a column with a non-null constraint
    private int quantity;

    // Constructor
    public Item() {}

    public Item(int itemID, String name, String description, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Utility method to print details
    public void getDetails() {
        System.out.println("Item: " + name + ", Description: " + description + ", Quantity: " + quantity);
    }
}
