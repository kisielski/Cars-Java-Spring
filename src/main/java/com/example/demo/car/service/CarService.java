package com.example.demo.car.service;

import com.example.demo.car.repository.CarRepository;
import com.example.demo.car.entity.Car;
import com.example.demo.engine.entity.Engine;
import com.example.demo.engine.repository.EngineRepository;
import com.example.demo.wheel.entity.Wheel;
import com.example.demo.wheel.repostiory.WheelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository repository;

    private EngineRepository engineRepository;

    private WheelRepository wheelRepository;

    @Autowired
    public CarService(CarRepository repository, EngineRepository engineRepository, WheelRepository wheelRepository) {
        this.repository = repository;
        this.engineRepository = engineRepository;
        this.wheelRepository = wheelRepository;
    }

    public Optional<Car> find(String registrationNumbers) { return repository.findById(registrationNumbers); }

    public Optional<Car> find(String registrationNumbers, String engineModel) {
        Optional<Engine> engine = engineRepository.findById(engineModel);
        if (engine.isPresent()) {
            return repository.findByRegistrationNumbersAndEngine(registrationNumbers, engine.get());
        } else {
            Optional<Wheel> wheel = wheelRepository.findById(engineModel);
            if (wheel.isPresent()) {
                return repository.findByRegistrationNumbersAndWheel(registrationNumbers, wheel.get());
            }
            else {
                return Optional.empty();
            }
        }
    }

    public List<Car> findAll() { return repository.findAll(); }

    public List<Car> findAll(Engine engine) { return repository.findAllByEngine(engine); }

    public List<Car> findAll(Wheel wheel) { return repository.findAllByWheel(wheel); }

    @Transactional
    public Car create(Car car) { return repository.save(car); }

    @Transactional
    public void delete(String id) { repository.deleteById(id); }

    @Transactional
    public void update(Car car) { repository.save(car); }
}
