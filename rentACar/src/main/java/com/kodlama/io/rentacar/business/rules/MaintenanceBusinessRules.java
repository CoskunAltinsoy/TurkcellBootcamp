package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.entities.enums.CarState;
import com.kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository maintenanceRepository;

    public void checkIfCarIsInMaintenance(int carId){
        if(maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(carId)){
            throw new RuntimeException("This car is in maintenance.");
        }
    }
    public void checkIfCarIsNotInMaintenance(int carId) {
        if (!maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new RuntimeException("This car is no in maintenance");
        }
    }

    public void checkCarAvailabilityForMaintenance(CarState carState){
        if(carState.equals(CarState.AVAILABLE)){
            throw new RuntimeException("This car cannot be serviced. This car is rented");
        }
    }

    public void checkIfMaintenanceExistsById(int id){
        if(!maintenanceRepository.existsById(id)){
            throw new RuntimeException("This maintenance info is not registered in the system.");
        }
    }
}
