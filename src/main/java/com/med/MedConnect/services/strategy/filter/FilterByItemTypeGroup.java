package com.med.MedConnect.services.strategy.filter;

import java.util.List;

class FilterByItemTypeGroup implements FilterStrategy {
    @Override
    public List<?> filter(String searchQuery) {
        System.out.println("Filtering by item type group for query: " + searchQuery);
        return null;
    }
}
