package com.med.MedConnect.services.strategy.filter;

public class FilterProcessor {
    private FilterStrategy strategy;

    public void setStrategy(FilterStrategy strategy) {
        this.strategy = strategy;
    }

    public void processFilter(String searchQuery) {
        if (strategy == null) {
            System.out.println("No filter strategy selected.");
        } else {
            strategy.filter(searchQuery);
        }
    }
}
