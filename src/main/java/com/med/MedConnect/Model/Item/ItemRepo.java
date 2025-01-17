package com.med.MedConnect.Model.Item;

import com.med.MedConnect.Model.Item.Item;
import com.med.MedConnect.Model.Item.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

}
