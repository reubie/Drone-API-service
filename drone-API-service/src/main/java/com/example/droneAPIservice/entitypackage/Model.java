package com.example.droneAPIservice.entitypackage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "weight_model")
@Data
@NoArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @Column(name = "weight_value")
    private int value;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Name cannot be null")
    private ModelEnum name;

    @OneToOne(mappedBy = "model")
    @JsonBackReference
    private Drone drone;

    public Model(ModelEnum name) {
        this.setValue(name);
    }

    public void setValue(ModelEnum name) {
        switch (name) {
            case LIGHT_WEIGHT:
                this.value = 100;
                this.name = ModelEnum.LIGHT_WEIGHT;
                break;
            case MIDDLE_WEIGHT:
                this.value = 200;
                this.name = ModelEnum.MIDDLE_WEIGHT;
                break;
            case CRUISER_WEIGHT:
                this.value = 300;
                this.name = ModelEnum.CRUISER_WEIGHT;
                break;
            case HEAVY_WEIGHT:
                this.value = 500;
                this.name = ModelEnum.HEAVY_WEIGHT;
                break;
            default:
                this.value = 100;
                this.name = ModelEnum.LIGHT_WEIGHT;
                break;
        }
    }

    public void setValue() {
        if (this.name != null) {
            this.setValue(this.name);
        }
    }

    public void setValue(int value) {
        this.setValue();
    }
}