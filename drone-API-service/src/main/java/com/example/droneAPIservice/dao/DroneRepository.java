package com.example.droneAPIservice.dao;

import com.example.droneAPIservice.entitypackage.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {

}