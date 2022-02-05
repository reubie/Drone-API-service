package com.example.droneAPIservice.dao;

import com.example.droneAPIservice.entitypackage.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

}

