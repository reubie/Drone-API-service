package com.example.droneAPIservice.serviceImplimentationPackage;

import com.example.droneAPIservice.dao.CustomDroneRepository;
import com.example.droneAPIservice.dao.DroneRepository;
import com.example.droneAPIservice.dao.MedicationRepository;
import com.example.droneAPIservice.dao.SerialNumberRepository;
import com.example.droneAPIservice.datatransferpackage.DroneDataTransfer;
import com.example.droneAPIservice.entitypackage.Drone;
import com.example.droneAPIservice.entitypackage.Medication;
import com.example.droneAPIservice.entitypackage.Model;
import com.example.droneAPIservice.entitypackage.SerialNumber;
import com.example.droneAPIservice.services.DroneServices;
import com.example.droneAPIservice.utilities.RequirementNotMetException;
import com.example.droneAPIservice.utilities.ResourceNotFoundException;
import com.example.droneAPIservice.utilities.State;
import com.github.olili2017.O2019.SerialNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneServices {

    private final DroneRepository droneRepository;

    private final CustomDroneRepository cDroneRepository;

    private final MedicationRepository medicationRepository;

    private final SerialNumberRepository sNumberRepository;

    private final ModelMapper mapper;

    @Override
    public Drone register(DroneDataTransfer drone) throws RequirementNotMetException, IllegalArgumentException {
        Optional<Long> optDroneCount = Optional.ofNullable(this.droneRepository.count());
        if (optDroneCount.isPresent() && optDroneCount.get() < 10) {
            try {
                String serialNumber = this.generateSerialNumber();
                boolean exists = this.sNumberRepository.findByValue(serialNumber) != null;
                while (exists) {
                    serialNumber = this.generateSerialNumber();
                    exists = this.sNumberRepository.findByValue(serialNumber) != null;
                }
                SerialNumber number = new SerialNumber(serialNumber);
                Model model = new Model(drone.getModel().getName());

                Drone newDrone = new Drone(number, model);
                newDrone.setBatteryCapacity(100);
                newDrone.setState(State.IDLE);
                number.setDrone(newDrone);
                return this.droneRepository.save(newDrone);
            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException("Drone cannot be null");
            }
        } else {
            throw new RequirementNotMetException("Maximumm number of drones that can be created is 10");
        }
    }

    @Override
    public List<Drone> findAvailable(int totalWeight) {
        return this.cDroneRepository.findAvailable(totalWeight);
    }

    @Override
    public Drone load(List<Medication> medications, Long droneId)
            throws ResourceNotFoundException, IllegalArgumentException, RequirementNotMetException {
        try {
            Drone drone = this.droneRepository.findById(droneId)
                    .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));

            int totalMedicationWeight = medications.stream().map(Medication::getWeight).reduce(0, Integer::sum);
            boolean isAvailable = this.findAvailable(totalMedicationWeight).stream()
                    .anyMatch(dr -> dr.getId() == droneId);
            if (isAvailable && drone.getBatteryCapacity() >= 25) {
                drone.setState(State.LOADING);
                this.droneRepository.save(drone);
                drone.setMedications(medications);
                drone.setState(State.LOADED);
                Drone savedDrone = this.droneRepository.save(drone);
                for (int i = 0; i < medications.size(); i++) {
                    Medication med = medications.get(i);
                    med.setDrone(drone);
                    medicationRepository.save(med);
                }
                return savedDrone;
            } else {
                drone.setState(State.IDLE);
                this.droneRepository.save(drone);
                String errorMessage = isAvailable
                        ? "Drone battery capacity is below 25%"
                        : "Total medication weight is above drone model specification or Drone is not in Idle state";
                throw new RequirementNotMetException(errorMessage);
            }
        } catch (IllegalArgumentException exc) {
            throw new IllegalArgumentException("Drone cannot be null");
        }
    }

    @Override
    public Drone findById(long id) throws ResourceNotFoundException {
        Drone drone = this.droneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
        return drone;
    }

    @Override
    public Drone update(DroneDataTransfer drone) throws ResourceNotFoundException, IllegalArgumentException {
        Optional<Long> id = Optional.ofNullable(drone.getId());
        Drone existingDrone = this.droneRepository
                .findById(id.orElseThrow(() -> new ResourceNotFoundException("Drone not found. id is null")))
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
        SerialNumber oldSerialNumber = existingDrone.getSerialNumber();
        mapper.map(drone, existingDrone);
        if (drone.getModel() != null) {
            Model model = (Model) drone.getModel();
            model.setValue();
            existingDrone.setModel(model);
        }
        existingDrone.setSerialNumber(oldSerialNumber); // serial number should not be updated
        try {
            this.droneRepository.save(existingDrone);
            return existingDrone;
        } catch (IllegalArgumentException exc) {
            throw new IllegalArgumentException("Drone cannot be null");
        }
    }

    @Override
    public List<Medication> getMedications(long droneId) throws ResourceNotFoundException {
        Drone drone = this.droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
        Optional<List<Medication>> optMeds = Optional.of(drone.getMedications());
        return optMeds.orElse(Collections.emptyList());
    }

    @Override
    public void delete(long id) throws ResourceNotFoundException, IllegalArgumentException {
        Drone drone = this.droneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
        try {
            this.droneRepository.delete(drone);
        } catch (IllegalArgumentException exc) {
            throw new IllegalArgumentException("Drone cannot be null");
        }
    }

    private String generateSerialNumber() {
        // TODO: use UUId to generate serial number
        return new SerialNumberGenerator(30).split(6, '-').generate();
    }
}
