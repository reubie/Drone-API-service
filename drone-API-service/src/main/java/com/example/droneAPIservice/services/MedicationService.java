package com.example.droneAPIservice.services;

import com.example.droneAPIservice.datatransferpackage.MedicationDataTransfer;
import com.example.droneAPIservice.entitypackage.Drone;
import com.example.droneAPIservice.entitypackage.Medication;
import com.example.droneAPIservice.utilities.ResourceNotFoundException;

public interface MedicationService {

    public Medication save(MedicationDataTransfer medication) throws IllegalArgumentException;

    public Medication findById(long id) throws ResourceNotFoundException;

    public void delete(long id) throws ResourceNotFoundException, IllegalArgumentException;

    public Medication update(MedicationDataTransfer dto) throws ResourceNotFoundException, IllegalArgumentException;

    public Medication imageUpdate(Long id, byte[] imageData) throws ResourceNotFoundException, IllegalArgumentException;

    public Drone checkLoadedMedications(long droneId) throws ResourceNotFoundException;

}