package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.CarService;
import com.kodlama.io.rentacar.business.abstracts.MaintenanceService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.kodlama.io.rentacar.entities.Car;
import com.kodlama.io.rentacar.entities.Maintenance;
import com.kodlama.io.rentacar.entities.enums.CarState;
import com.kodlama.io.rentacar.repository.MaintenanceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final CarService carService;
    private final ModelMapper modelMapper;

    public MaintenanceManager(
            MaintenanceRepository maintenanceRepository,
            CarService carService,
            ModelMapper modelMapper
    ) {
        this.maintenanceRepository = maintenanceRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = this.maintenanceRepository.findAll();
        List<GetAllMaintenancesResponse> getAllMaintenancesResponses = maintenances
                .stream()
                .map(maintenance -> modelMapper.map(maintenance, GetAllMaintenancesResponse.class))
                .collect(Collectors.toList());

        return getAllMaintenancesResponses;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance = this.maintenanceRepository.findById(id).orElseThrow();
        GetMaintenanceResponse getMaintenanceResponse =
                modelMapper.map(maintenance, GetMaintenanceResponse.class);
        return getMaintenanceResponse;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest) {
        checkIfCarIsInMaintenance(createMaintenanceRequest.getCarId());
        checkCarAvailabilityForMaintenance(createMaintenanceRequest.getCarId());

        Maintenance maintenance = modelMapper.map(createMaintenanceRequest, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDate.now());
        maintenance.setEndDate(LocalDate.now());
        carService.changeCarState(createMaintenanceRequest.getCarId(), CarState.MAINTENANCE);

        this.maintenanceRepository.save(maintenance);

        CreateMaintenanceResponse createMaintenanceResponse =
                modelMapper.map(maintenance, CreateMaintenanceResponse.class);
        return createMaintenanceResponse;
    }

    @Override
    public UpdateMaintenanceResponse update(UpdateMaintenanceRequest updateMaintenanceRequest) {
        checkIfMaintenanceExistsById(updateMaintenanceRequest.getId());
        Maintenance maintenance = modelMapper.map(updateMaintenanceRequest, Maintenance.class);
        this.maintenanceRepository.save(maintenance);

        UpdateMaintenanceResponse updateMaintenanceResponse =
                modelMapper.map(maintenance, UpdateMaintenanceResponse.class);
        return updateMaintenanceResponse;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExistsById(id);
        this.maintenanceRepository.deleteById(id);
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int id) {
        checkIfCarIsNotInMaintenance(id);
        Maintenance maintenance = maintenanceRepository.findByCarIdAndIsCompletedIsFalse(id);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDate.now());
        maintenanceRepository.save(maintenance);
        carService.changeCarState(id, CarState.AVAILABLE);
        GetMaintenanceResponse getMaintenanceResponse =
                modelMapper.map(maintenance, GetMaintenanceResponse.class);
        return getMaintenanceResponse;
    }
    private void checkIfCarIsInMaintenance(int id){
        if(maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(id)){
            throw new RuntimeException("This car is in maintenance.");
        }
    }
    private void checkIfCarIsNotInMaintenance(int id) {
        if (!maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(id)) {
            throw new RuntimeException("This car is no in maintenance");
        }
    }

    private void checkCarAvailabilityForMaintenance(int id){
        if(!carService.getById(id).getCarState().equals(CarState.AVAILABLE)){
            throw new RuntimeException("This car cannot be serviced. This car is rented");
        }
    }

    private void checkIfMaintenanceExistsById(int id){
        if(!maintenanceRepository.existsById(id)){
            throw new RuntimeException("This maintenance info is not registered in the system.");
        }
    }


}
