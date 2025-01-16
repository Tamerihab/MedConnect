package com.med.MedConnect.services.strategy.filter;

public class FilterByRecentGroup implements FilterStrategy {
    @Override
    public void filter(String searchQuery) {
        System.out.println("Filtering by recent group for query: " + searchQuery);
    }
}
