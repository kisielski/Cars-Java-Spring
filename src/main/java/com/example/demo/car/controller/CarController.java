package com.example.demo.car.controller;

import com.example.demo.car.dto.CreateCarRequest;
import com.example.demo.car.dto.GetCarResponse;
import com.example.demo.car.dto.GetCarsResponse;
import com.example.demo.car.dto.UpdateCarRequest;
import com.example.demo.car.service.CarService;
import com.example.demo.engine.service.EngineService;
import com.example.demo.wheel.service.WheelService;
import com.example.demo.car.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Optional;

@RestController
@RequestMapping("api/cars")
public class CarController {

    private CarService carService;

    private EngineService engineService;

    private WheelService wheelService;

    @Autowired
    public CarController(CarService carService, EngineService engineService, WheelService wheelService) {
        this.carService = carService;
        this.engineService = engineService;
        this.wheelService = wheelService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars() {
        return ResponseEntity.ok(GetCarsResponse.entityToDtoMapper().apply(carService.findAll()));
    }

    @GetMapping("{registration_numbers}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("registration_numbers") String registrationNumbers) {
        Optional<Car> car = carService.find(registrationNumbers);
        return car.map(value -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CreateCarRequest request, UriComponentsBuilder builder) {
        Car car = CreateCarRequest
                .dtoToEntityMapper(wheelModel -> wheelService.find(wheelModel).orElseThrow(), engineModel -> engineService.find(engineModel).orElseThrow())
                .apply(request);
        car = carService.create(car);
        return ResponseEntity.created(builder.pathSegment("api", "cars", "{registration_numbers}").buildAndExpand(car.getRegistrationNumbers()).toUri()).build();
    }

    @DeleteMapping("{registration_numbers}")
    public ResponseEntity<Void> deleteCar(@PathVariable("registration_numbers") String registrationNumbers) {
        Optional<Car> car = carService.find(registrationNumbers);
        if (car.isPresent()) {
            carService.delete(car.get().getRegistrationNumbers());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{registration_numbers}")
    public ResponseEntity<Void> updateCar(@RequestBody UpdateCarRequest request, @PathVariable("registration_numbers") String registrationNumbers) {
        Optional<Car> car = carService.find(registrationNumbers);
        if (car.isPresent()) {
            UpdateCarRequest.dtoToEntityUpdater().apply(car.get(), request);
            carService.update(car.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
