package com.med.MedConnect.services.command;

import com.med.MedConnect.services.strategy.search.SearchProcessor;
import com.med.MedConnect.services.strategy.search.SearchStrategy;
import java.util.List;


public class SearchCommand implements Command {
    private final SearchProcessor searchProcessor;
    private final String searchQuery;
    private final SearchStrategy searchStrategy;

    public SearchCommand(SearchProcessor searchProcessor, String searchQuery, SearchStrategy searchStrategy) {
        this.searchProcessor = searchProcessor;
        this.searchQuery = searchQuery;
        this.searchStrategy = searchStrategy;
    }

    @Override
    public List<?> execute() {
        searchProcessor.setSearchStrategy(searchStrategy);
        return searchProcessor.executeSearch(searchQuery);
    }
}
