package com.example.demo.controller;

import com.example.demo.dto.engine.CreateEngineRequest;
import com.example.demo.dto.engine.GetEngineResponse;
import com.example.demo.dto.engine.GetEnginesResponse;
import com.example.demo.dto.engine.UpdateEngineRequest;
import com.example.demo.service.EngineService;
import com.example.demo.vehicletype.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/engines")
public class EngineController {

    private EngineService engineService;

    @Autowired
    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @GetMapping
    public ResponseEntity<GetEnginesResponse> getEngines() {
        return ResponseEntity.ok(GetEnginesResponse.entityToDtoMapper().apply(engineService.findAll()));
    }

    @GetMapping("{model}")
    public ResponseEntity<GetEngineResponse> getEngine(@PathVariable("model") String model) {
        Optional<Engine> engine = engineService.find(model);
        return engine.map(value -> ResponseEntity.ok(GetEngineResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createEngine(@RequestBody CreateEngineRequest request, UriComponentsBuilder builder) {
        Engine engine = CreateEngineRequest
                .dtoToEntityMapper()
                .apply(request);
        engine = engineService.create(engine);
        return ResponseEntity.created(builder.pathSegment("api", "engines", "{model}").buildAndExpand(engine.getModel()).toUri()).build();
    }

    @DeleteMapping("{model}")
    public ResponseEntity<Void> deleteEngine(@PathVariable("model") String model) {
        Optional<Engine> engine = engineService.find(model);
        if (engine.isPresent()) {
            engineService.delete(engine.get().getModel());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{model}")
    public ResponseEntity<Void> updateEngine(@RequestBody UpdateEngineRequest request, @PathVariable("model") String model) {
        Optional<Engine> engine = engineService.find(model);
        if (engine.isPresent()) {
            UpdateEngineRequest.dtoToEntityUpdater().apply(engine.get(), request);
            engineService.update(engine.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}