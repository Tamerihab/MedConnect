package com.med.MedConnect.Model.Donation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepo extends JpaRepository<Donation, Integer> {
    // Additional query methods can be added if needed
}
