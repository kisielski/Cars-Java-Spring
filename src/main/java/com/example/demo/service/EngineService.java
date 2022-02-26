package com.example.demo.service;

import com.example.demo.repository.EngineRepository;
import com.example.demo.vehicletype.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EngineService {

    private EngineRepository repository;

    @Autowired
    public EngineService(EngineRepository repository) {
        this.repository = repository;
    }

    public Optional<Engine> find(String id) {
        return repository.findById(id);
    }

    public List<Engine> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Engine create(Engine engine) {
        return repository.save(engine);
    }

    @Transactional
    public void delete(String model) { repository.deleteById(model); }

    @Transactional
    public void update(Engine engine) { repository.save(engine); }

}
