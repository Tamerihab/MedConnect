package com.med.MedConnect.services.strategy.search;

public class SearchByMedincine implements SearchStrategy {
    @Override
    public void search(String searchQuery) {
        System.out.println("Searching for medicine: " + searchQuery);

//        MedicineRepository.findByNameContainingIgnoreCase(searchQuery)
//                .forEach(medicine -> System.out.println(medicine.getName()));
    }
}
