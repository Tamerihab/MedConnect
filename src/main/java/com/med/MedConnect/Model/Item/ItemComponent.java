package com.med.MedConnect.Model.Item;

import java.util.Iterator;

public interface ItemComponent {
    void getDetails();

    void add(ItemComponent itemComponent); // Composite operation
    void remove(ItemComponent itemComponent); // Composite operation
    ItemComponent getChild(int index); // Composite operation

    int getQuantity(); // Leaf operation
    String getName(); // Leaf operation
    String getDescription(); // Leaf operation

    Iterator<ItemComponent> createIterator(); // Iterator operation
}
