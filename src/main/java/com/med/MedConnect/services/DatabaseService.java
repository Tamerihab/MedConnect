package com.med.MedConnect.services;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.Item.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private static DatabaseService instance;

    @Autowired
    private static ItemRepo itemRepo;

    private DatabaseService() {}

    @Autowired
    public void setItemRepo(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public static synchronized DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public static List<Item> getAllItems() {
        if (itemRepo == null) {
            throw new IllegalStateException("ItemRepo not initialized!");
        }
        return itemRepo.findAll();
    }
}
