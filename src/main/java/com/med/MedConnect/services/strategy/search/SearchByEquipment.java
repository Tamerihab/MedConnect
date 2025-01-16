package com.med.MedConnect.services.strategy.search;

public class SearchByEquipment implements SearchStrategy {
    @Override
    public void search(String searchQuery) {
        System.out.println("Searching for equipment: " + searchQuery);
    }
}
