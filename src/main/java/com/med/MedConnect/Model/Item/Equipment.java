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
    private Condition condition;


    public Equipment() {
        super();
        setType(ItemType.EQUIPMENT);
    }

    // Parameterized constructor
    public Equipment(String name, String description,Condition condition) {
        super(name, description, ItemType.EQUIPMENT);  // Assuming ItemType is an enum with EQUIPMENT
        this.condition = condition;

    }


    @Override
    public void getDetails() {
        System.out.println("Type: EQUIPMENT, Name: " + getName() + ", Description: " + getDescription() +
              ", Condition: " + condition);
    }
}
