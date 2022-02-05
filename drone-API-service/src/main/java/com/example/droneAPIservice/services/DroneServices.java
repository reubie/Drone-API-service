package com.example.droneAPIservice.services;

import com.example.droneAPIservice.datatransferpackage.DroneDataTransfer;
import com.example.droneAPIservice.entitypackage.Drone;
import com.example.droneAPIservice.entitypackage.Medication;
import com.example.droneAPIservice.utilities.RequirementNotMetException;
import com.example.droneAPIservice.utilities.ResourceNotFoundException;

import java.util.List;

public interface DroneServices {
    public Drone register(DroneDataTransfer drone) throws IllegalArgumentException, RequirementNotMetException;

    public List<Drone> findAvailable(int totalWeight);

    public Drone load(List<Medication> medications, Long droneId)
            throws ResourceNotFoundException, IllegalArgumentException, RequirementNotMetException;

    public Drone findById(long id) throws ResourceNotFoundException;

    public void delete(long id) throws ResourceNotFoundException, IllegalArgumentException;

    public Drone update(DroneDataTransfer drone) throws ResourceNotFoundException, IllegalArgumentException;

    public List<Medication> getMedications(long droneId) throws ResourceNotFoundException;
}
