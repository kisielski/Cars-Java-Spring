package com.example.demo.dto.wheel;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetWheelsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Wheel {

        private String model;

        private int size;

        private String color;

    }

    @Singular
    private List<Wheel> wheels;

    public static Function<Collection<com.example.demo.vehicletype.Wheel>, GetWheelsResponse> entityToDtoMapper() {
        return wheels -> {
            GetWheelsResponseBuilder response = GetWheelsResponse.builder();
            wheels.stream()
                    .map(wheel -> Wheel.builder()
                            .model(wheel.getModel())
                            .size(wheel.getSize())
                            .color(wheel.getColor())
                            .build())
                    .forEach(response::wheel);
            return response.build();
        };
    }
}
