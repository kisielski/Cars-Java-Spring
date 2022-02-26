package com.example.demo.repository;

import com.example.demo.vehicletype.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, String> {

}
