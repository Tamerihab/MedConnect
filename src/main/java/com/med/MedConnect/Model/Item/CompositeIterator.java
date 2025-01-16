package com.med.MedConnect.Model.Item;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements Iterator<ItemComponent> {
    private Stack<Iterator<ItemComponent>> stack = new Stack<>();

    public CompositeIterator(Iterator<ItemComponent> iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        } else {
            Iterator<ItemComponent> iterator = stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public ItemComponent next() {
        if (hasNext()) {
            Iterator<ItemComponent> iterator = stack.peek();
            ItemComponent component = iterator.next();
            if (component instanceof Item) {
                stack.push(component.createIterator());
            }
            return component;
        } else {
            return null;
        }
    }
}
