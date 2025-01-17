package com.med.MedConnect.services.strategy.filter;

import java.util.List;

public class FilterByRecentGroup implements FilterStrategy {
    @Override
    public List<?> filter(String searchQuery) {
        System.out.println("Filtering by recent group for query: " + searchQuery);
        return null;
    }
}
