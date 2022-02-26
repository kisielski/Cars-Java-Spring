package com.example.demo.dto.engine;

import com.example.demo.vehicletype.Engine;
import lombok.*;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateEngineRequest {

    private double displacement;

    private int horsepower;

    private int torque;

    private String model;

    public static Function<CreateEngineRequest, Engine> dtoToEntityMapper() {
        return request -> Engine.builder()
                .model(request.getModel())
                .displacement(request.getDisplacement())
                .horsepower(request.getHorsepower())
                .torque(request.getTorque())
                .build();
    }

}

