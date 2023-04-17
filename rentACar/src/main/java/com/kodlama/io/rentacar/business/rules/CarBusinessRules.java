package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.entities.Car;
import com.kodlama.io.rentacar.entities.enums.CarState;
import com.kodlama.io.rentacar.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBusinessRules {
    private final CarRepository carRepository;

    public CarBusinessRules(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void checkIfCarExistsById(int id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("This car is not registered in the system");
        }
    }



}
