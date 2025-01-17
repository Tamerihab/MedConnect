package com.med.MedConnect.services.strategy.filter;

import java.util.List;

class FilterByUrgencyGroup implements FilterStrategy {
    @Override
    public List filter(String searchQuery) {
        System.out.println("Filtering by urgency group for query: " + searchQuery);
        return null;
    }
}
