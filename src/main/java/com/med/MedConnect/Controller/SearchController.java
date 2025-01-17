package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.services.command.CommandInvoker;
import com.med.MedConnect.services.command.SearchCommand;
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
    private CommandInvoker commandInvoker;

    @Autowired
    private SearchProcessor searchProcessor;

    @Autowired
    private SearchByMedicine searchByMedicine;

    @Autowired
    private SearchByEquipment searchByEquipment;

    // Search for medicine
    @GetMapping("/search/medicine")
    public List<?> searchMedicine(@RequestParam String searchQuery) {
        SearchCommand searchCommand = new SearchCommand(searchProcessor, searchQuery, searchByMedicine);

        // Return the result from the CommandInvoker
        return commandInvoker.executeCommand(searchCommand);
    }

    // Search for equipment using Command pattern
    @GetMapping("/search/equipment")
    public List<?> searchEquipment(@RequestParam String searchQuery) {
        // Create the SearchCommand with the SearchProcessor, search query, and the SearchByEquipment strategy
        SearchCommand searchCommand = new SearchCommand(searchProcessor, searchQuery, searchByEquipment);

        // Return the result from the CommandInvoker
        return commandInvoker.executeCommand(searchCommand);
    }


}
