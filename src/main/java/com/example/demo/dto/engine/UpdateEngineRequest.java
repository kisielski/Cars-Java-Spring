package com.example.demo.dto.engine;


import com.example.demo.vehicletype.Engine;
import lombok.*;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateEngineRequest {

    private int horsepower;

    private int torque;

    public static BiFunction<Engine, UpdateEngineRequest, Engine> dtoToEntityUpdater() {
        return (engine, request) -> {
            engine.setHorsepower(request.getHorsepower());
            engine.setTorque(request.getTorque());
            return engine;
        };
    }

}
