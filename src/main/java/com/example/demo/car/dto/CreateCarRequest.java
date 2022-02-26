package com.example.demo.car.dto;

import com.example.demo.car.entity.Car;
import com.example.demo.engine.entity.Engine;
import com.example.demo.wheel.entity.Wheel;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCarRequest {

    private String registrationNumbers;

    private float maxVelocity;

    private String model;

    private int numberOfDoors;

    private int seats;

    private float tankVolume;

    private String wheel;

    private String engine;

    public static Function<CreateCarRequest, Car> dtoToEntityMapper(
            Function<String, Wheel> wheelFunction,
            Function<String, Engine> engineFunction) {
        return request -> Car.builder()
                .registrationNumbers(request.getRegistrationNumbers())
                .maxVelocity(request.getMaxVelocity())
                .model(request.getModel())
                .numberOfDoors(request.getNumberOfDoors())
                .seats(request.getSeats())
                .tankVolume(request.getTankVolume())
                .wheel(wheelFunction.apply(request.getWheel()))
                .engine(engineFunction.apply(request.getEngine()))
                .build();
    }

    public static Function<CreateCarRequest, Car> dtoToEntityMapper(
            Function<String, Wheel> wheelFunction,
            Supplier<Engine> engineSupplier) {
        return request -> Car.builder()
                .registrationNumbers(request.getRegistrationNumbers())
                .maxVelocity(request.getMaxVelocity())
                .model(request.getModel())
                .numberOfDoors(request.getNumberOfDoors())
                .seats(request.getSeats())
                .tankVolume(request.getTankVolume())
                .wheel(wheelFunction.apply(request.getWheel()))
                .engine(engineSupplier.get())
                .build();
    }

    public static Function<CreateCarRequest, Car> dtoToEntityMapper(
            Supplier<Wheel> wheelSupplier,
            Function<String, Engine> engineFunction) {
        return request -> Car.builder()
                .registrationNumbers(request.getRegistrationNumbers())
                .maxVelocity(request.getMaxVelocity())
                .model(request.getModel())
                .numberOfDoors(request.getNumberOfDoors())
                .seats(request.getSeats())
                .tankVolume(request.getTankVolume())
                .wheel(wheelSupplier.get())
                .engine(engineFunction.apply(request.getEngine()))
                .build();
    }
}
