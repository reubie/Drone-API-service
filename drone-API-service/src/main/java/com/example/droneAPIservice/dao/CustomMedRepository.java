package com.example.droneAPIservice.dao;

import com.example.droneAPIservice.entitypackage.Drone;
import com.example.droneAPIservice.utilities.ResourceNotFoundException;

public interface CustomMedRepository {

    public Drone checkLoadedMedications(long droneId) throws ResourceNotFoundException;

}
