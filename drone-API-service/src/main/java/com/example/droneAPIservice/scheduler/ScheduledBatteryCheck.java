package com.example.droneAPIservice.scheduler;

import com.example.droneAPIservice.dao.AuditDroneRepository;
import com.example.droneAPIservice.dao.DroneRepository;
import com.example.droneAPIservice.entitypackage.AuditDrone;
import com.example.droneAPIservice.entitypackage.Drone;
import com.example.droneAPIservice.utilities.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ScheduledBatteryCheck {
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private AuditDroneRepository aDroneRepository;

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void batteryCheck() {
        List<Drone> drones = droneRepository.findAll();
        drones.stream().forEach(drone -> {
            try {
                int batteryCapacity = drone.getBatteryCapacity();
                State state = drone.getState();
                AuditDrone aDrone = new AuditDrone(state, batteryCapacity);
                aDrone.setDroneId(drone.getId());
                aDroneRepository.save(aDrone);
            } catch (IllegalArgumentException exc) {
                throw new IllegalArgumentException("Drone not found");
            }
        });
    }

}

