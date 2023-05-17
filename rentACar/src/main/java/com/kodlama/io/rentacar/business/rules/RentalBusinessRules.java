package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.entities.Car;
import com.kodlama.io.rentacar.entities.enums.CarState;
import com.kodlama.io.rentacar.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository rentalRepository;

    public void checkIfCarAvailable(CarState carState){
        if(!carState.equals(CarState.AVAILABLE)){
            throw new RuntimeException("Car is not available");
        };
    }

    public void checkIfRentalExistsById(int id) {
        if (!rentalRepository.existsById(id)) {
            throw new RuntimeException("There is no rental information available");
        }
    }
    public void checkIfCarInRented(int carId){
        if(rentalRepository.existsByCarIdAndIsCompletedIsFalse(carId)){
            throw new RuntimeException("This car is in rented");
        }
    }

    public void checkIfCarIsNotInRented(int carId) {
        if (!rentalRepository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new RuntimeException("This car is no in rented");
        }
    }

}
