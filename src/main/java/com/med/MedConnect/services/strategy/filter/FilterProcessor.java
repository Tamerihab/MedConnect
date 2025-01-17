package com.med.MedConnect.services.strategy.filter;

import java.util.List;

public class FilterProcessor {
    private FilterStrategy strategy;

    public void setStrategy(FilterStrategy strategy) {
        this.strategy = strategy;
    }

    public List<?> processFilter(String searchQuery) {
        if (strategy == null) {
            System.out.println("No filter strategy selected.");
        }

        return strategy.filter(searchQuery);

    }
}
