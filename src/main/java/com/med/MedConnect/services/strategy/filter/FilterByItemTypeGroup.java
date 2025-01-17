package com.med.MedConnect.services.strategy.filter;

class FilterByItemTypeGroup implements FilterStrategy {
    @Override
    public void filter(String searchQuery) {
        System.out.println("Filtering by item type group for query: " + searchQuery);
    }
}
