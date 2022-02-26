package com.example.demo.vehicletype;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
@Entity
@Table(name="engines")
public class Engine {

    private double displacement;

    private int horsepower;

    private int torque;

    @Id
    private String model;

}
