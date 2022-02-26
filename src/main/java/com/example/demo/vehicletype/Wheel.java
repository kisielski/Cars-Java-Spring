package com.example.demo.vehicletype;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
@Entity
@Table(name="wheels")
public class Wheel {

    private int size;

    @Id
    private String model;

    private String color;

}
