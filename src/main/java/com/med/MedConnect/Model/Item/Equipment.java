package com.med.MedConnect.Model.Item;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String manufacturer;

    @Column
    private String warranty;

    // Default constructor for JPA
    public Equipment() {
        super();
    }

    // Parameterized constructor
    public Equipment(String name, String description, double price, String manufacturer, String warranty) {
        super(name, description, price, ItemType.EQUIPMENT);  // Assuming ItemType is an enum with EQUIPMENT
        this.manufacturer = manufacturer;
        this.warranty = warranty;
    }

    // Getter and Setter methods for the new fields
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    @Override
    public void getDetails() {
        System.out.println("Type: EQUIPMENT, Name: " + getName() + ", Description: " + getDescription() +
                ", Price: " + getPrice() + ", Manufacturer: " + manufacturer + ", Warranty: " + warranty);
    }
}
