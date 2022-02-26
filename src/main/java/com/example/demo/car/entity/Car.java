package com.example.demo.car.entity;

import com.example.demo.vehicle.Vehicle;
import com.example.demo.engine.entity.Engine;
import com.example.demo.wheel.entity.Wheel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car extends Vehicle {

    private String model;

    @Column(name="number_of_doors")
    private int numberOfDoors;

    private int seats;

    @Column(name="tank_volume")
    private float tankVolume;

    @ManyToOne
    @JoinColumn(name="wheel")
    private Wheel wheel;

    @ManyToOne
    @JoinColumn(name="engine")
    private Engine engine;

    public String toString() {
        return "Car(registrationNumbers=" + getRegistrationNumbers() + ", maxVelocity=" + getMaxVelocity() + ", model=" + model + ", numberOfDoors=" + numberOfDoors + ", seats=" + seats + ", tankVolume=" + tankVolume + ", engine model=" + engine.getModel() + ", wheels model=" + wheel.getModel() + ")";
    }
}
