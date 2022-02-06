package com.example.droneAPIservice.entitypackage;

import com.example.droneAPIservice.utilities.State;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "audit_drone")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DroneMonitorClass extends CheckableClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "battery_capacity")
    @Range(min = 0, max = 100)
    private int batteryCapacity;

    @Column(name = "drone_id")
    private long droneId;

    public DroneMonitorClass(State state, int batteryCapacity) {
        super();
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }
}
