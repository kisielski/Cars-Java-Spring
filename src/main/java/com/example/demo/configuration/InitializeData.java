package com.example.demo.configuration;

import com.example.demo.car.service.CarService;
import com.example.demo.engine.service.EngineService;
import com.example.demo.wheel.service.WheelService;
import com.example.demo.car.entity.Car;
import com.example.demo.engine.entity.Engine;
import com.example.demo.wheel.entity.Wheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeData {

    private final CarService carService;

    private final EngineService engineService;

    private final WheelService wheelService;

    @Autowired
    public InitializeData(CarService carService, EngineService engineService, WheelService wheelService) {
        this.carService = carService;
        this.engineService = engineService;
        this.wheelService = wheelService;
    }

    @PostConstruct
    private void init() {
        Engine engine1 = Engine.builder()
                .model("M54B25")
                .build();
        Engine engine2 = Engine.builder()
                .model("138_B2.000")
                .build();
        Engine engine3 = Engine.builder()
                .model("2JZ-GE_R6")
                .build();
        engineService.create(engine1);
        engineService.create(engine2);
        engineService.create(engine3);

        Wheel wheel1 = Wheel.builder()
                .model("BBS_Super_RS")
                .build();
        Wheel wheel2 = Wheel.builder()
                .model("JapanRacing_JR9")
                .build();
        Wheel wheel3 = Wheel.builder()
                .model("MAM_B2_BFO")
                .build();
        Wheel wheel4 = Wheel.builder()
                .model("OZ_RallyRacing")
                .build();
        wheelService.create(wheel1);
        wheelService.create(wheel2);
        wheelService.create(wheel3);
        wheelService.create(wheel4);

        Car car1 = Car.builder()
                .model("BMW e60")
                .numberOfDoors(4)
                .seats(5)
                .tankVolume(62)
                .maxVelocity(290)
                .registrationNumbers("NO_95FG3")
                .engine(engine1)
                .wheel(wheel3)
                .build();
        Car car2 = Car.builder()
                .model("Fiat Uno")
                .numberOfDoors(3)
                .seats(4)
                .tankVolume(45)
                .maxVelocity(160)
                .registrationNumbers("GD_D48GP")
                .engine(engine2)
                .wheel(wheel2)
                .build();
        Car car3 = Car.builder()
                .model("Lexus is200")
                .numberOfDoors(4)
                .seats(5)
                .tankVolume(55)
                .maxVelocity(250)
                .registrationNumbers("GD_5OP8I")
                .engine(engine3)
                .wheel(wheel3)
                .build();
        carService.create(car1);
        carService.create(car2);
        carService.create(car3);
    }
}
