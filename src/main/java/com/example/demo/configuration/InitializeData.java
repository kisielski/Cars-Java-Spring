package com.example.demo.configuration;

import com.example.demo.service.EngineService;
import com.example.demo.service.WheelService;
import com.example.demo.vehicletype.Engine;
import com.example.demo.vehicletype.Wheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeData {

    private final EngineService engineService;

    private final WheelService wheelService;

    @Autowired
    public InitializeData(EngineService engineService, WheelService wheelService) {
        this.engineService = engineService;
        this.wheelService = wheelService;
    }

    @PostConstruct
    private void init() {
        Engine engine1 = Engine.builder()
                .displacement(2.5)
                .horsepower(192)
                .torque(245)
                .model("M54B25")
                .build();
        Engine engine2 = Engine.builder()
                .displacement(1.3)
                .horsepower(68)
                .torque(98)
                .model("138_B2.000")
                .build();
        Engine engine3 = Engine.builder()
                .displacement(3.0)
                .horsepower(214)
                .torque(288)
                .model("2JZ-GE_R6")
                .build();
        engineService.create(engine1);
        engineService.create(engine2);
        engineService.create(engine3);

        Wheel wheel1 = Wheel.builder()
                .size(20)
                .model("BBS_Super_RS")
                .color("chrome")
                .build();
        Wheel wheel2 = Wheel.builder()
                .size(15)
                .model("JapanRacing_JR9")
                .color("chrome")
                .build();
        Wheel wheel3 = Wheel.builder()
                .size(19)
                .model("MAM_B2_BFO")
                .color("red")
                .build();
        Wheel wheel4 = Wheel.builder()
                .size(18)
                .model("OZ_RallyRacing")
                .color("white")
                .build();
        wheelService.create(wheel1);
        wheelService.create(wheel2);
        wheelService.create(wheel3);
        wheelService.create(wheel4);
    }
}
