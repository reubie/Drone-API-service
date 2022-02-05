package com.example.droneAPIservice.dao;

import com.example.droneAPIservice.entitypackage.Drone;

import java.util.List;

public interface CustomDroneRepository {

    public List<Drone> findAvailable(int totalWeight);

}