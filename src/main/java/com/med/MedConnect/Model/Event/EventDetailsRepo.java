package com.med.MedConnect.Model.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailsRepo extends JpaRepository<EventDetails, Integer> {
}