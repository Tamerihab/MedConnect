package com.med.MedConnect.Model.Item;

import java.util.Iterator;

public class NullIterator implements Iterator<ItemComponent> {
    public ItemComponent next() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }
}
