package com.med.MedConnect.services.command;
import com.med.MedConnect.services.strategy.filter.FilterProcessor;

import java.util.List;


public class FilterCommand implements Command {

    private final FilterProcessor filterProcessor;
    private final String filterQuery;

    public FilterCommand(FilterProcessor filterProcessor, String filterQuery) {
        this.filterProcessor = filterProcessor;
        this.filterQuery = filterQuery;
    }

    @Override
    public List<?> execute() {
        return filterProcessor.processFilter(filterQuery);
    }
}