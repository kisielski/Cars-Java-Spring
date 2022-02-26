package com.example.demo.car.dto;

import com.example.demo.car.entity.Car;
import lombok.*;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCarRequest {

    private int seats;

    private float tankVolume;

    public static BiFunction<Car, UpdateCarRequest, Car> dtoToEntityUpdater() {
        return (car, request) -> {
            car.setSeats(request.getSeats());
            car.setTankVolume(request.getTankVolume());
            return car;
        };
    }

}
