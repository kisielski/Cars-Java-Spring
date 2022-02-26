package com.example.demo.wheel.controller;


import com.example.demo.wheel.dto.CreateWheelRequest;
import com.example.demo.wheel.service.WheelService;
import com.example.demo.wheel.entity.Wheel;
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

    @PostMapping("")
    public ResponseEntity<Void> createWheel(@RequestBody CreateWheelRequest request, UriComponentsBuilder builder) {
        Wheel wheel = CreateWheelRequest
                .dtoToEntityMapper()
                .apply(request);
        wheel = wheelService.create(wheel);
        return ResponseEntity.created(builder.pathSegment("api", "wheels", "{model}").buildAndExpand(wheel.getModel()).toUri()).build();
    }
}

