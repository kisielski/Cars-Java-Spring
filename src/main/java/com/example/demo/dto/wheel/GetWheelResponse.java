package com.example.demo.dto.wheel;

import com.example.demo.vehicletype.Wheel;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetWheelResponse {

    private String model;

    private int size;

    private String color;

    public static Function<Wheel, GetWheelResponse> entityToDtoMapper() {
        return wheel -> GetWheelResponse.builder()
                .model(wheel.getModel())
                .size(wheel.getSize())
                .color(wheel.getColor())
                .build();
    }
}
