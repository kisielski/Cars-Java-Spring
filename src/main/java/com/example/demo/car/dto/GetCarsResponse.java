package com.example.demo.car.dto;

import lombok.*;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCarsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Car {

        private String registrationNumbers;

        private String model;

        private float maxVelocity;

    }

    @Singular
    private List<Car> cars;

    public static Function<Collection<com.example.demo.car.entity.Car>, GetCarsResponse> entityToDtoMapper() {
        return cars -> {
            GetCarsResponseBuilder response = GetCarsResponse.builder();
            cars.stream()
                    .map(car -> Car.builder()
                            .registrationNumbers(car.getRegistrationNumbers())
                            .model(car.getModel())
                            .maxVelocity(car.getMaxVelocity())
                            .build())
                    .forEach(response::car);
            return response.build();
        };
    }
}
