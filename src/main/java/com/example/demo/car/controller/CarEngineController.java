package com.example.demo.car.controller;

import com.example.demo.car.dto.CreateCarRequest;
import com.example.demo.car.dto.GetCarResponse;
import com.example.demo.car.dto.GetCarsResponse;
import com.example.demo.car.dto.UpdateCarRequest;
import com.example.demo.car.entity.Car;
import com.example.demo.car.service.CarService;
import com.example.demo.engine.entity.Engine;
import com.example.demo.engine.service.EngineService;
import com.example.demo.wheel.service.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/engines/{engine}/cars")
public class CarEngineController {

    private CarService carService;

    private EngineService engineService;

    private WheelService wheelService;

    @Autowired
    public CarEngineController(CarService carService, EngineService engineService, WheelService wheelService) {
        this.carService = carService;
        this.engineService = engineService;
        this.wheelService = wheelService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars(@PathVariable("engine") String engineModel) {
        Optional<Engine> engine = engineService.find(engineModel);
        return engine.map(value -> ResponseEntity.ok(GetCarsResponse.entityToDtoMapper().apply(carService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{registration_numbers}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("engine") String engineModel,
                                                       @PathVariable("registration_numbers") String registration) {
        return carService.find(registration, engineModel)
                .map(value -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@PathVariable("engine") String engineModel,
                                                @RequestBody CreateCarRequest request,
                                                UriComponentsBuilder builder) {
        Optional<Engine> engine = engineService.find(engineModel);
        if (engine.isPresent()) {
            Car car = CreateCarRequest
                    .dtoToEntityMapper(wheelModel -> wheelService.find(wheelModel).orElseThrow(), engine::get)
                    .apply(request);
            car = carService.create(car);
            return ResponseEntity.created(builder.pathSegment("api", "engines", "{engine}", "cars", "{registration_numbers}")
                    .buildAndExpand(engine.get().getModel(), car.getRegistrationNumbers()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{registration_numbers}")
    public ResponseEntity<Void> deleteCar(@PathVariable("engine") String engineModel,
                                                @PathVariable("registration_numbers") String registration) {
        Optional<Car> car = carService.find(registration, engineModel);
        if (car.isPresent()) {
            carService.delete(car.get().getRegistrationNumbers());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{registration_numbers}")
    public ResponseEntity<Void> updateCar(@PathVariable("engine") String engineModel,
                                                @RequestBody UpdateCarRequest request,
                                                @PathVariable("registration_numbers") String registration) {
        Optional<Car> car = carService.find(registration, engineModel);
        if (car.isPresent()) {
            UpdateCarRequest.dtoToEntityUpdater().apply(car.get(), request);
            carService.update(car.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
