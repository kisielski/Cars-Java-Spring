package com.example.demo.wheel.entity;

import com.example.demo.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
@Entity
@Table(name="wheels")
public class Wheel {

    @Id
    private String model;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "wheel")
    @ToString.Exclude
    private List<Car> cars;
}
