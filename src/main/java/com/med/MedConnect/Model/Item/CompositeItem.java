package com.med.MedConnect.Model.Item;
import java.util.Iterator;
import java.util.ArrayList;

public class CompositeItem implements ItemComponent {
    Iterator<ItemComponent> iterator = null;
    ArrayList<ItemComponent> itemComponents = new ArrayList<ItemComponent>();
    private String name;
    private String description;
    private double price;

    public CompositeItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void addChild(ItemComponent itemComponent) {
        itemComponents.add(itemComponent);
    }

    public void removeChild(ItemComponent itemComponent) {
        itemComponents.remove(itemComponent);
    }

    @Override
    public ItemComponent getChild(int i){
        return itemComponents.get(i);
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

    public Iterator<ItemComponent> createIterator() {
        if (iterator == null) {
            iterator = new CompositeIterator(itemComponents.iterator());
        }
        return iterator;
    }

    @Override
    public void getDetails() {
            System.out.print("\n" + getName());
            System.out.println(", " + getDescription());
            System.out.println(", " + getPrice());
            System.out.println("---------------------");

            Iterator<ItemComponent> iterator = itemComponents.iterator();
            while (iterator.hasNext()) {
                ItemComponent menuComponent = iterator.next();
                menuComponent.getDetails();
            }
    }
}
