package com.example.demo.dto.wheel;


import com.example.demo.vehicletype.Wheel;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateWheelRequest {

    private String color;

    public static BiFunction<Wheel, UpdateWheelRequest, Wheel> dtoToEntityUpdater() {
        return (wheel, request) -> {
            wheel.setColor(request.getColor());
            return wheel;
        };
    }

}
