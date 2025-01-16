package com.med.MedConnect.services.strategy.search;

public class SearchByHospital implements SearchStrategy {
    @Override
    public void search(String searchQuery) {
        System.out.println("Searching for hospital: " + searchQuery);
    }
}
