package com.med.MedConnect.services.strategy.search;

public class SearchByEquipment implements SearchStrategy {
    @Override
    public void search(String searchQuery) {
        System.out.println("Searching for equipment: " + searchQuery);

        // Query the database for matching hospital names
        EquipmentRepository.findByNameContainingIgnoreCase(searchQuery)
                .forEach(equipment -> System.out.println(equipment.getName()));
    }
}
