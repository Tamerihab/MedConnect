package com.med.MedConnect.Model.Item;

import jakarta.persistence.*;

@Entity
@Table(name = "medicine")
public class Medicine extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @Column
    private String manufacturer;

    @Column
    private String expiryDate;

    // Default constructor for JPA
    public Medicine() {
        super();
        setType(ItemType.MEDICINE);
    }

    // Parameterized constructor
//    public Medicine(String name, String description, double price, String manufacturer, String expiryDate) {
//        super(name, description, price, ItemType.MEDICINE);  // Assuming ItemType is an enum with MEDICINE
//        this.manufacturer = manufacturer;
//        this.expiryDate = expiryDate;
//    }

    // Getter and Setter methods for the new fields
    public String getManufacturer() {
        return manufacturer;
    }



    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public void getDetails() {
        System.out.println("Type: MEDICINE, Name: " + getName() + ", Description: " + getDescription() +
                ", Price: " + getPrice() + ", Manufacturer: " + manufacturer + ", Expiry Date: " + expiryDate);
    }
}
