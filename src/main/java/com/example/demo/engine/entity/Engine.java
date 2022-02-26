package com.example.demo.engine.entity;

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
@Table(name="engines")
public class Engine {

    @Id
    private String model;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "engine")
    @ToString.Exclude
    private List<Car> cars;
}
