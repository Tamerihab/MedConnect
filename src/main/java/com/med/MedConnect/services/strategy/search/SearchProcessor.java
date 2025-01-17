package com.med.MedConnect.services.strategy.search;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchProcessor {
    private SearchStrategy searchStrategy;

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<?> executeSearch(String searchQuery) {
        if (searchStrategy == null) {
            throw new IllegalStateException("Search strategy not set!");
        }
        return searchStrategy.search(searchQuery);
    }
}
