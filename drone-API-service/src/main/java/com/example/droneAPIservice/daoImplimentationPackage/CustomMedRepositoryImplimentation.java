package com.example.droneAPIservice.daoImplimentationPackage;

import com.example.droneAPIservice.dao.AppRepository;
import com.example.droneAPIservice.dao.CustomMedRepository;

import com.example.droneAPIservice.entitypackage.Drone;
import com.example.droneAPIservice.entitypackage.Medication;
import com.example.droneAPIservice.utilities.ResourceNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomMedRepositoryImplimentation implements CustomMedRepository {

    @Autowired
    private AppRepository appRepository;

    @Override
    public Drone checkLoadedMedications(long droneId) throws ResourceNotFoundException {
        QMedication medication = QMedication.medication;
        JPAQuery<Medication> mJpaQuery = appRepository.startJPAQuery(medication);
        Optional<Medication> med = Optional.ofNullable(mJpaQuery.where(medication.drone.id.eq(droneId)).fetchFirst());
        return med.orElseThrow(() -> new ResourceNotFoundException("Drone could not be found")).getDrone();
    }

}
