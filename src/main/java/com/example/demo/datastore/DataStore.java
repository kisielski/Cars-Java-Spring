package com.example.demo.datastore;

import com.example.demo.vehicletype.Engine;
import com.example.demo.vehicletype.Wheel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataStore {

    private List<Engine> engines = new ArrayList<>();

    private List<Wheel> wheels = new ArrayList<>();

    public List<Engine> getEnginesList() {
        return engines;
    }

    public List<Wheel> getWheelsList() {
        return wheels;
    }
}
