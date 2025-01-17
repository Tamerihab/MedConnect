package com.med.MedConnect.Model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepo extends JpaRepository<PaymentDetails, Integer> {
}
