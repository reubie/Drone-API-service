package com.example.droneAPIservice.dao;

import com.example.droneAPIservice.entitypackage.AuditDrone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditDroneRepository extends JpaRepository<AuditDrone, Long> {

}