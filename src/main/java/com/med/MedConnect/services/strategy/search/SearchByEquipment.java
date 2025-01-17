package com.med.MedConnect.services.strategy.search;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.Item.ItemRepo;
import com.med.MedConnect.Model.Item.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchByEquipment implements SearchStrategy {

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public List<Item> search(String searchQuery) {
        System.out.println("Searching for equipment: " + searchQuery);

        // Search for items of type EQUIPMENT and containing the search query in the name
        List<Item> equipment = itemRepo.findByNameContainingIgnoreCaseAndType(searchQuery, ItemType.EQUIPMENT);

        // Display the results
        equipment.forEach(equip -> System.out.println("Found: " + equip.getName() + ", " + equip.getDescription()));
        return equipment;
    }
}
