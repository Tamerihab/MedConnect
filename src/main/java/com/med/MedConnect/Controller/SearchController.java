package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.services.strategy.search.SearchProcessor;
import com.med.MedConnect.services.strategy.search.SearchByMedicine;
import com.med.MedConnect.services.strategy.search.SearchByEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class SearchController {

    @Autowired
    private SearchProcessor searchProcessor;

    @Autowired
    private SearchByMedicine searchByMedicine;

    @Autowired
    private SearchByEquipment searchByEquipment;

    // Search for medicine
    @GetMapping("/search/medicine")
    public List<?> searchMedicine(@RequestParam String searchQuery) {
        // Set the search strategy to SearchByMedicine
        searchProcessor.setSearchStrategy(searchByMedicine);
        // Execute the search
       return searchProcessor.executeSearch(searchQuery);
    }


    // Search for equipment
    @GetMapping("/search/equipment")
    public List<?> searchEquipment(@RequestParam String searchQuery) {
        // Set the search strategy to SearchByEquipment
        searchProcessor.setSearchStrategy(searchByEquipment);
        // Execute the search
        return searchProcessor.executeSearch(searchQuery);
    }
}
