package com.example.droneAPIservice.entitypackage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "serial_number")
@Data
@NoArgsConstructor
public class SerialNumber {

    @Id
    @Column(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @NotBlank(message = "Valid serial number is required")
    @Size(max = 100)
    private String value;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Drone drone;

    public SerialNumber(String value) {
        this.value = value;
    }

}