package com.med.MedConnect.Model.Item;

import java.util.*;

public class CompositeIterator implements Iterator<ItemComponent> {
    Stack<Iterator<ItemComponent>> stack = new Stack<Iterator<ItemComponent>>();

    public CompositeIterator(Iterator<ItemComponent> iterator) {
        stack.push(iterator);
    }

    public ItemComponent next() {
        if (hasNext()) {
            Iterator<ItemComponent> iterator = stack.peek();
            ItemComponent component = iterator.next();
            stack.push(component.createIterator());
            return component;
        } else {
            return null;
        }
    }

    public boolean hasNext() {
        if (stack.empty()) {
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
}

