package com.example.demo.controller;


import com.example.demo.dto.wheel.CreateWheelRequest;
import com.example.demo.dto.wheel.GetWheelResponse;
import com.example.demo.dto.wheel.GetWheelsResponse;
import com.example.demo.dto.wheel.UpdateWheelRequest;
import com.example.demo.service.WheelService;
import com.example.demo.vehicletype.Wheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/wheels")
public class WheelController {

    private WheelService wheelService;

    @Autowired
    public WheelController(WheelService wheelService) {
        this.wheelService = wheelService;
    }

    @GetMapping
    public ResponseEntity<GetWheelsResponse> getWheels() {
        return ResponseEntity.ok(GetWheelsResponse.entityToDtoMapper().apply(wheelService.findAll()));
    }

    @GetMapping("{model}")
    public ResponseEntity<GetWheelResponse> getWheel(@PathVariable("model") String model) {
        Optional<Wheel> wheel = wheelService.find(model);
        return wheel.map(value -> ResponseEntity.ok(GetWheelResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createWheel(@RequestBody CreateWheelRequest request, UriComponentsBuilder builder) {
        Wheel wheel = CreateWheelRequest
                .dtoToEntityMapper()
                .apply(request);
        wheel = wheelService.create(wheel);
        return ResponseEntity.created(builder.pathSegment("api", "wheels", "{model}").buildAndExpand(wheel.getModel()).toUri()).build();
    }

    @DeleteMapping("{model}")
    public ResponseEntity<Void> deleteWheel(@PathVariable("model") String model) {
        Optional<Wheel> wheel = wheelService.find(model);
        if (wheel.isPresent()) {
            wheelService.delete(wheel.get().getModel());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{model}")
    public ResponseEntity<Void> updateWheel(@RequestBody UpdateWheelRequest request, @PathVariable("model") String model) {
        Optional<Wheel> wheel = wheelService.find(model);
        if (wheel.isPresent()) {
            UpdateWheelRequest.dtoToEntityUpdater().apply(wheel.get(), request);
            wheelService.update(wheel.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

