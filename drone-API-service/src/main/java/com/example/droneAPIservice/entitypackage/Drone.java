package com.example.droneAPIservice.entitypackage;

import com.example.droneAPIservice.utilities.State;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drone")
@Data
@NoArgsConstructor
@ToString(exclude = { "medications" })
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @NotNull
    @OneToOne(mappedBy = "drone", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private SerialNumber serialNumber;

    @Column(name = "battery_capacity")
    @Range(min = 0, max = 100)
    private int batteryCapacity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @NotNull(message = "Valid model type is required")
    @JsonManagedReference
    private Model model;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "drone", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Medication> medications;

    public Drone(SerialNumber serialNumber, Model model) {
        this.serialNumber = serialNumber;
        this.model = model;
    }

}
