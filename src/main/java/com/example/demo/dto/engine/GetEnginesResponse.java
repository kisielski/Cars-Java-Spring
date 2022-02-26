package com.example.demo.dto.engine;

import lombok.*;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetEnginesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Engine {

        private String model;

        private double displacement;

        private int horsepower;

    }

    @Singular
    private List<GetEnginesResponse.Engine> engines;

    public static Function<Collection<com.example.demo.vehicletype.Engine>, GetEnginesResponse> entityToDtoMapper() {
        return engines -> {
            GetEnginesResponse.GetEnginesResponseBuilder response = GetEnginesResponse.builder();
            engines.stream()
                    .map(engine -> GetEnginesResponse.Engine.builder()
                            .model(engine.getModel())
                            .displacement(engine.getDisplacement())
                            .horsepower(engine.getHorsepower())
                            .build())
                    .forEach(response::engine);
            return response.build();
        };
    }
}
