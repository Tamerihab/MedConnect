package com.med.MedConnect.services.strategy.search;

class SearchProcessor {
    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void processSearch(String searchQuery) {
        if (strategy == null) {
            System.out.println("No search strategy selected.");
        } else {
            strategy.search(searchQuery);
        }
    }
}
