package com.med.MedConnect.Controller;
import com.med.MedConnect.Model.Item.Equipment;
import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.Item.ItemService;


import com.med.MedConnect.Model.Item.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

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
}
