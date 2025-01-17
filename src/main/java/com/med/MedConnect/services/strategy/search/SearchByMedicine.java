package com.med.MedConnect.services.strategy.search;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.Item.ItemRepo;
import com.med.MedConnect.Model.Item.Medicine;
import com.med.MedConnect.Model.Item.ItemType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchByMedicine implements SearchStrategy {

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public void search(String searchQuery) {
        System.out.println("Searching for medicine: " + searchQuery);

        // Search for items of type MEDICINE and containing the search query in the name
        List<Medicine> medicines = itemRepo.findByNameContainingIgnoreCaseAndType(searchQuery, ItemType.MEDICINE);
        //     // Search for items of type MEDICINE and containing the search query in the name
        //        List<Item> medicines = itemRepo.findByNameContainingIgnoreCaseAndType(searchQuery, ItemType.MEDICINE);
        // Display the results
        medicines.forEach(medicine -> System.out.println("Found: " + medicine.getName() + ", " + medicine.getDescription()));
    }
}
