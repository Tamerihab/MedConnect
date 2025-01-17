package com.med.MedConnect.Model.Item;

import jakarta.persistence.*;

import java.util.Iterator;

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
    private double price;

    @Enumerated(EnumType.STRING)
    @Column
    private ItemType type;

    public Item() {
    }

    public Item(String name, String description, double price, ItemType type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
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
