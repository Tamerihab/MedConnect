package com.med.MedConnect.Model.Item;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;


    @Enumerated(EnumType.STRING)
    @Column(name = "item_condition")
    @JsonProperty("condition")
    private Condition itemCondition;


    public Equipment() {
        super();
        setType(ItemType.EQUIPMENT);
    }

    // Parameterized constructor
    public Equipment(String name, String description,Condition itemCondition,int quantity) {
        super(name, description, ItemType.EQUIPMENT,quantity); // Assuming ItemType is an enum with EQUIPMENT
        this.itemCondition = itemCondition;

    }


    @Override
    public void getDetails() {
        System.out.println("Type: EQUIPMENT, Name: " + getName() + ", Description: " + getDescription() +
              ", Condition: " + itemCondition + ", Quantity: " + getQuantity());
    }
}
