package com.example.droneAPIservice.entitypackage;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "medication")
@Data
@NoArgsConstructor
@ToString(exclude = { "drone" })
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @Column(name = "name")
    @Pattern(regexp = "^[\\w-]+$")
    @NotBlank(message = "Valid name is required")
    private String name;

    @Column(name = "weight")
    @NotNull(message = "Weight is required")
    private int weight;

    @Column(name = "code")
    @Pattern(regexp = "^[A-Z0-9_]{11,15}$")
    @NotBlank(message = "Valid code is required")
    private String code;

    @Column(name = "image", columnDefinition = "BLOB")
    @Lob
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_id")
    @JsonBackReference
    private Drone drone;

    public Medication(String name, int weight, String code) {
        this.name = name;
        this.weight = weight;
        this.code = code;
    }

}