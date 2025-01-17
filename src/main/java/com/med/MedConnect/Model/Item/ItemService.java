package com.med.MedConnect.Model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public List<Item> getAllItems() {
        return new ArrayList<>(itemRepo.findAll());
    }
//    public Item saveItem(Item item) {
//        return itemRepo.save(item);
//    }

    // Method to save a new Medicine item
    public Medicine saveMedicine(Medicine medicine) {
        return (Medicine) itemRepo.save(medicine);  // Save and return the Medicine object
    }

    // Method to save a new Equipment item
    public Equipment saveEquipment(Equipment equipment) {
        return (Equipment) itemRepo.save(equipment);  // Save and return the Equipment object
    }
}
