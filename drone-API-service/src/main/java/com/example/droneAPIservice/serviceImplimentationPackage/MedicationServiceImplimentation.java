package com.example.droneAPIservice.serviceImplimentationPackage;

import com.example.droneAPIservice.dao.CustomMedRepository;
import com.example.droneAPIservice.dao.MedicationRepository;
import com.example.droneAPIservice.datatransferpackage.MedicineDataTransfer;
import com.example.droneAPIservice.entitypackage.Drone;
import com.example.droneAPIservice.entitypackage.Medication;
import com.example.droneAPIservice.services.MedicationService;
import com.example.droneAPIservice.utilities.ResourceNotFoundException;
import com.mifmif.common.regex.Generex;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class MedicationServiceImplimentation implements MedicationService {

    private final ModelMapper mapper;

    private final MedicationRepository medicationRepository;

    private final CustomMedRepository cMedRepository;

    @Override
    public Medication save(MedicineDataTransfer medication) throws IllegalArgumentException {
        String code = this.getCode();
        medication.setCode(code);
        Medication newMed = new Medication();
        mapper.map(medication, newMed);
        Medication savedMed = this.medicationRepository.save(newMed);
        return savedMed;
    }

    @Override
    public Medication findById(long id) throws ResourceNotFoundException {
        Medication medication = this.medicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found"));
        return medication;
    }

    @Override
    public Drone checkLoadedMedications(long droneId) throws ResourceNotFoundException {
        return this.cMedRepository.checkLoadedMedications(droneId);
    }

    @Override
    public void delete(long id) throws ResourceNotFoundException, IllegalArgumentException {
        Medication medication = this.medicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found"));
        try {
            this.medicationRepository.delete(medication);
        } catch (IllegalArgumentException exc) {
            throw new IllegalArgumentException("Medication cannot be null");
        }
    }

    @Override
    public Medication update(MedicineDataTransfer dto) throws ResourceNotFoundException, IllegalArgumentException {
        Optional<Long> id = Optional.ofNullable(dto.getId());
        Medication medication = this.medicationRepository
                .findById(id.orElseThrow(() -> new ResourceNotFoundException("Medication not found. id is null")))
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found"));
        mapper.map(dto, medication);
        try {
            this.medicationRepository.save(medication);
            return medication;
        } catch (IllegalArgumentException exc) {
            throw new IllegalArgumentException("Medication cannot be null");
        }
    }

    @Override
    public Medication imageUpdate(Long id, byte[] imageData)
            throws ResourceNotFoundException, IllegalArgumentException {
        Medication medication = this.medicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found"));
        medication.setImage(imageData);
        try {
            this.medicationRepository.save(medication);
            return medication;
        } catch (IllegalArgumentException exc) {
            throw new IllegalArgumentException("Medication cannot be null");
        }
    }

    private String getCode() {
        Generex generex = new Generex("[A-Z0-9]{11,15}_*");
        return generex.random();
    }
}