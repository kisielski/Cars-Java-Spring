package com.example.demo.dto.engine;

import com.example.demo.vehicletype.Engine;
import lombok.*;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetEngineResponse {

    private double displacement;

    private int horsepower;

    private int torque;

    private String model;

    public static Function<Engine, GetEngineResponse> entityToDtoMapper() {
        return engine -> GetEngineResponse.builder()
                .model(engine.getModel())
                .displacement(engine.getDisplacement())
                .horsepower(engine.getHorsepower())
                .torque(engine.getTorque())
                .build();
    }
}

