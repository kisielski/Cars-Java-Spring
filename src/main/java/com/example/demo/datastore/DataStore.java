package com.example.demo.datastore;

import com.example.demo.car.entity.Car;
import com.example.demo.engine.entity.Engine;
import com.example.demo.wheel.entity.Wheel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataStore {

    private List<Car> cars = new ArrayList<>();

    private List<Engine> engines = new ArrayList<>();

    private List<Wheel> wheels = new ArrayList<>();

    public List<Car> getCarsList() {
        return cars;
    }

    public List<Engine> getEnginesList() {
        return engines;
    }

    public List<Wheel> getWheelsList() {
        return wheels;
    }

}
