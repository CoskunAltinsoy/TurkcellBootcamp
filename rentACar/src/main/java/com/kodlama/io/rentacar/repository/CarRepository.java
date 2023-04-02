package com.kodlama.io.rentacar.repository;

import com.kodlama.io.rentacar.entities.Car;
import com.kodlama.io.rentacar.entities.enums.CarState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> findAllCarByCarState(CarState carState);
}
