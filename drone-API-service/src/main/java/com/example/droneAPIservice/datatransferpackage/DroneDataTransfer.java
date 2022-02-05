package com.example.droneAPIservice.datatransferpackage;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ui.Model;

import java.util.List;
@Data
@NoArgsConstructor
public class DroneDataTransfer {

    @NotNull()
    private Model model;

    private Long id;

    private SerialNumber serialNumber;

    private int batteryCapacity;

    private State state;

    private List<Medication> medications;
}
