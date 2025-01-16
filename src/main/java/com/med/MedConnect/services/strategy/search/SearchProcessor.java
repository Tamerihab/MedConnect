package com.med.MedConnect.services.strategy.search;

class SearchProcessor {
    private SearchStrategy searchStrategy;

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void executeSearch(String searchQuery) {
        if (searchStrategy == null) {
            throw new IllegalStateException("Search strategy not set!");
        }
        searchStrategy.search(searchQuery);
    }
}
