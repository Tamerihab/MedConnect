package com.med.MedConnect.services.strategy.filter;

class FilterByUrgencyGroup implements FilterStrategy {
    @Override
    public void filter(String searchQuery) {
        System.out.println("Filtering by urgency group for query: " + searchQuery);
    }
}
