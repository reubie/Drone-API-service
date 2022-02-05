package com.example.droneAPIservice.datatransferpackage;

import com.example.droneAPIservice.entitypackage.Drone;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class MedicationDataTransfer {
    private long id;

    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[\\w-]+$")
    private String name;

    @NotNull(message = "Weight is required")
    private int weight;

    @Pattern(regexp = "^[A-Z0-9_]{11,15}$")
    private String code;

    private Drone drone;

    public MedicationDataTransfer(String name, int weight, String code) {
        this.name = name;
        this.weight = weight;
        this.code = code;
    }

}