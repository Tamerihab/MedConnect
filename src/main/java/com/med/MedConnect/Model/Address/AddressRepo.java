package com.med.MedConnect.Model.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {
    // Fetch countries (parent_id = 0)
    List<Address> findByParentIsNull();
    
    // Fetch cities based on the parent country ID
    List<Address> findByParent_addressId(int parentId);
}

