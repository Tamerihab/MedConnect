package com.med.MedConnect.Controller;
import com.med.MedConnect.Model.Item.*;


import com.med.MedConnect.services.strategy.search.SearchByEquipment;
import com.med.MedConnect.services.strategy.search.SearchByMedicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepo itemRepo;

    // Endpoint to get all items
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

//    // Endpoint to save a new item (could be either Medicine or Equipment)
//    @PostMapping
//    public Item saveItem(@RequestBody Item item) {
//        return itemService.saveItem(item);
//    }
// Endpoint to save a new Medicine item
    @PostMapping("/medicine")
    public Medicine saveMedicine(@RequestBody Medicine medicine) {
        return itemService.saveMedicine(medicine);
    }

    // Endpoint to save a new Equipment item
    @PostMapping("/equipment")
    public Equipment saveEquipment(@RequestBody Equipment equipment) {
        return itemService.saveEquipment(equipment);
    }

//    @Autowired
//    private SearchByMedicine searchByMedicine;
//
//    @Autowired
//    private SearchByEquipment searchByEquipment;

//    @GetMapping("/search/medicine")
//    public List<Item> searchMedicine(@RequestParam String searchQuery) {
//        return searchByMedicine.search(searchQuery);
//    }
//    @GetMapping("/search/equipment")
//    public List<Item> searchEquipment(@RequestParam String searchQuery) {
//        return searchByEquipment.search(searchQuery);
//        //return itemRepo.findByNameContainingIgnoreCaseAndType(searchQuery, ItemType.EQUIPMENT);
//    }

//    @GetMapping("/search/equipment")
//    public void searchEquipment(@RequestParam String query) {
//        searchByEquipment.search(query);
//    }
}
