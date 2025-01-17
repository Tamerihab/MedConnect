package com.med.MedConnect.services.strategy.search;

import com.med.MedConnect.Model.Item.Item;

import java.util.List;

public class SearchByHospital implements SearchStrategy {
    @Override
    public List<Item> search(String searchQuery) {
        System.out.println("Searching for hospital: " + searchQuery);

        // Query the database for matching hospital names
//        HospitalRepository.findByNameContainingIgnoreCase(searchQuery)
//                .forEach(hospital -> System.out.println(hospital.getName()));
        return null;
    }
}
