package com.med.MedConnect.services.strategy.filter;

import java.util.List;

public interface FilterStrategy {

    List<?> filter(String searchQuery);
}
