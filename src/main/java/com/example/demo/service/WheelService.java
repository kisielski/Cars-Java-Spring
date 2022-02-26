package com.example.demo.service;

import com.example.demo.repository.WheelRepository;
import com.example.demo.vehicletype.Wheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WheelService {

    private WheelRepository repository;

    @Autowired
    public WheelService(WheelRepository repository) {
        this.repository = repository;
    }

    public Optional<Wheel> find(String id) {
        return repository.findById(id);
    }

    public List<Wheel> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Wheel create(Wheel wheel) {
        return repository.save(wheel);
    }

    @Transactional
    public void delete(String id) { repository.deleteById(id); }

    @Transactional
    public void update(Wheel wheel) { repository.save(wheel); }

}
