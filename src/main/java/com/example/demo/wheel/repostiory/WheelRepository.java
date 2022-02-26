package com.example.demo.wheel.repostiory;

import com.example.demo.wheel.entity.Wheel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WheelRepository extends JpaRepository<Wheel, String> {

}
