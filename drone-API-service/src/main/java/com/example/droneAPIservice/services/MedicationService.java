package com.example.droneAPIservice.services;

import com.example.droneAPIservice.datatransferpackage.MedicineDataTransfer;
import com.example.droneAPIservice.entitypackage.Medication;
import com.example.droneAPIservice.utilities.ResourceNotFoundException;

public interface MedicationService {

    public Medication save(MedicineDataTransfer medication) throws IllegalArgumentException;

    public Medication findById(long id) throws ResourceNotFoundException;

    public void delete(long id) throws ResourceNotFoundException, IllegalArgumentException;

    public Medication update(MedicineDataTransfer dto) throws ResourceNotFoundException, IllegalArgumentException;

    public Medication imageUpdate(Long id, byte[] imageData) throws ResourceNotFoundException, IllegalArgumentException;

    public Long checkLoadedMedications(long droneId) throws ResourceNotFoundException;

}