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

    // Method to save a new item (could be either Medicine or Equipment)
    public Item saveItem(Item item) {
        return itemRepo.save(item);
    }
}
