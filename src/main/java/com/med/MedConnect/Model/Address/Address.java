package com.med.MedConnect.Model.Address;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "addressId")
    private Address parent; // Self-referencing relationship

    // Default constructor
    public Address() {
    }

    // Constructor for creating Address objects
    public Address(String name, Address parent) {
        this.name = name;
        this.parent = parent;
    }

    // Getters and setters
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getParent() {
        return parent;
    }

    public void setParent(Address parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", name='" + name + '\'' +
                ", parent=" + (parent != null ? parent.getName() : "None") +
                '}';
    }
}
