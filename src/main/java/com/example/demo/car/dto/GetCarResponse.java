package com.example.demo.car.dto;

import com.example.demo.car.entity.Car;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCarResponse {

    private String registrationNumbers;

    private String model;

    private int numberOfDoors;

    private int seats;

    private float tankVolume;

    private String wheelModel;

    private String engineModel;

    private float maxVelocity;

    public static Function<Car, GetCarResponse> entityToDtoMapper() {
        return car -> GetCarResponse.builder()
                .registrationNumbers(car.getRegistrationNumbers())
                .model(car.getModel())
                .numberOfDoors(car.getNumberOfDoors())
                .seats(car.getSeats())
                .tankVolume(car.getTankVolume())
                .wheelModel(car.getWheel().getModel())
                .engineModel(car.getEngine().getModel())
                .maxVelocity(car.getMaxVelocity())
                .build();
    }
}
