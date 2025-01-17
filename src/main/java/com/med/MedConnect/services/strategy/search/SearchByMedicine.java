package com.med.MedConnect.services.strategy.search;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.Item.ItemRepo;
import com.med.MedConnect.Model.Item.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchByMedicine implements SearchStrategy {

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public List<Item> search(String searchQuery) {
        System.out.println("Searching for medicine: " + searchQuery);

        List<Item> medicines = itemRepo.findByNameContainingIgnoreCaseAndType(searchQuery, ItemType.MEDICINE);

        if (medicines.isEmpty()) {
            System.out.println("No medicines found for the query: " + searchQuery);
        }

        medicines.forEach(medicine -> System.out.println("Found: " + medicine.getName() + ", " + medicine.getDescription()));
        return medicines;
    }

}
