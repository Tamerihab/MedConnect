package com.med.MedConnect.services.strategy.filter;

class FilterByHospitalGroup implements FilterStrategy {
    @Override
    public void filter(String searchQuery) {
        System.out.println("Filtering by hospital group for query: " + searchQuery);
    }
}
