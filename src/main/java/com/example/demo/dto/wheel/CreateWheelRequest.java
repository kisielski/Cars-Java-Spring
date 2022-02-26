package com.example.demo.dto.wheel;


import com.example.demo.vehicletype.Wheel;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateWheelRequest {

    private String model;

    private int size;

    private String color;

    public static Function<CreateWheelRequest, Wheel> dtoToEntityMapper() {
        return request -> Wheel.builder()
                .model(request.getModel())
                .size(request.getSize())
                .color(request.getColor())
                .build();
    }

}

