package com.example.droneAPIservice.dao;

import com.example.droneAPIservice.entitypackage.DroneMonitorClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditDroneRepository extends JpaRepository<DroneMonitorClass, Long> {

}