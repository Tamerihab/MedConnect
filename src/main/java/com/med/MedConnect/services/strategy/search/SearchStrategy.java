package com.med.MedConnect.services.strategy.search;

import com.med.MedConnect.Model.Item.Item;

import java.util.List;

public interface SearchStrategy {
    List<Item> search(String searchQuery);

}
