package com.example.demo.repository;

import com.example.demo.vehicletype.Wheel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WheelRepository extends JpaRepository<Wheel, String> {

}
