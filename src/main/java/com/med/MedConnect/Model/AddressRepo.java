package com.med.MedConnect.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

    // Correct query: Use parent addressId to find cities based on parent address (country)
    List<Address> findByParent_addressId(int parentId);  // Fetch cities based on parent.addressId

    // Fetch all countries (parent is null)
    List<Address> findByParentIsNull(); // Fetch countries with no parent (top-level addresses)
}
