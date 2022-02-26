package com.example.demo.wheel.dto;


import com.example.demo.wheel.entity.Wheel;
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
                .build();
    }

}

