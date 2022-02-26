package com.example.demo.engine.controller;

import com.example.demo.engine.dto.CreateEngineRequest;
import com.example.demo.engine.service.EngineService;
import com.example.demo.engine.entity.Engine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/engines")
public class EngineController {

    private EngineService engineService;

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

    @PostMapping("")
    public ResponseEntity<Void> createEngine(@RequestBody CreateEngineRequest request, UriComponentsBuilder builder) {
        Engine engine = CreateEngineRequest
                .dtoToEntityMapper()
                .apply(request);
        engine = engineService.create(engine);
        return ResponseEntity.created(builder.pathSegment("api", "engines", "{model}").buildAndExpand(engine.getModel()).toUri()).build();
    }
}