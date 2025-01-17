package com.med.MedConnect.Model.Item;

import java.util.Iterator;

public interface ItemComponent {
    public void addChild(ItemComponent itemComponent);
    public void removeChild(ItemComponent itemComponent);
    public ItemComponent getChild(int i);

    public abstract Iterator<ItemComponent> createIterator();
    public void getDetails();
}
