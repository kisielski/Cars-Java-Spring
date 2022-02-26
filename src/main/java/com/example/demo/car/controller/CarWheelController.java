package com.example.demo.car.controller;

import com.example.demo.car.dto.CreateCarRequest;
import com.example.demo.car.dto.GetCarResponse;
import com.example.demo.car.dto.GetCarsResponse;
import com.example.demo.car.dto.UpdateCarRequest;
import com.example.demo.car.entity.Car;
import com.example.demo.car.service.CarService;
import com.example.demo.engine.service.EngineService;
import com.example.demo.wheel.entity.Wheel;
import com.example.demo.wheel.service.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/wheels/{wheel}/cars")
public class CarWheelController {

    private CarService carService;

    private EngineService engineService;

    private WheelService wheelService;

    @Autowired
    public CarWheelController(CarService carService, EngineService engineService, WheelService wheelService) {
        this.carService = carService;
        this.engineService = engineService;
        this.wheelService = wheelService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars(@PathVariable("wheel") String wheelModel) {
        Optional<Wheel> wheel = wheelService.find(wheelModel);
        return wheel.map(value -> ResponseEntity.ok(GetCarsResponse.entityToDtoMapper().apply(carService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{registration_numbers}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("wheel") String wheelModel,
                                                       @PathVariable("registration_numbers") String registration) {
        return carService.find(registration, wheelModel)
                .map(value -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@PathVariable("wheel") String wheelModel,
                                                @RequestBody CreateCarRequest request,
                                                UriComponentsBuilder builder) {
        Optional<Wheel> wheel = wheelService.find(wheelModel);
        if (wheel.isPresent()) {
            Car car = CreateCarRequest
                    .dtoToEntityMapper(wheel::get, engineModel -> engineService.find(engineModel).orElseThrow())
                    .apply(request);
            car = carService.create(car);
            return ResponseEntity.created(builder.pathSegment("api", "wheels", "{wheel}", "cars", "{registration_numbers}")
                    .buildAndExpand(wheel.get().getModel(), car.getRegistrationNumbers()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{registration_numbers}")
    public ResponseEntity<Void> deleteCar(@PathVariable("wheel") String wheelModel,
                                          @PathVariable("registration_numbers") String registration) {
        Optional<Car> car = carService.find(registration, wheelModel);
        if (car.isPresent()) {
            carService.delete(car.get().getRegistrationNumbers());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{registration_numbers}")
    public ResponseEntity<Void> updateCar(@PathVariable("wheel") String wheelModel,
                                          @RequestBody UpdateCarRequest request,
                                          @PathVariable("registration_numbers") String registration) {
        Optional<Car> car = carService.find(registration, wheelModel);
        if (car.isPresent()) {
            UpdateCarRequest.dtoToEntityUpdater().apply(car.get(), request);
            carService.update(car.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
