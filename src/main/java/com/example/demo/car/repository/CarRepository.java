package com.example.demo.car.repository;

import com.example.demo.car.entity.Car;
import com.example.demo.engine.entity.Engine;
import com.example.demo.wheel.entity.Wheel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    Optional<Car> findByRegistrationNumbersAndEngine(final String registrationNumbers, Engine engine);

    Optional<Car> findByRegistrationNumbersAndWheel(final String registrationNumbers, Wheel wheel);

    List<Car> findAllByEngine(Engine engine);

    List<Car> findAllByWheel(Wheel wheel);
}
