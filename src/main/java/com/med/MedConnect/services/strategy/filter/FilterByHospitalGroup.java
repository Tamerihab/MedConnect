package com.med.MedConnect.services.strategy.filter;

import java.util.List;

class FilterByHospitalGroup implements FilterStrategy {
    @Override
    public List<?> filter(String searchQuery) {
        System.out.println("Filtering by hospital group for query: " + searchQuery);
        return null;
    }
}
