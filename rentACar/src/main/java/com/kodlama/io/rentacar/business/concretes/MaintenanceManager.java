package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.CarService;
import com.kodlama.io.rentacar.business.abstracts.MaintenanceService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.kodlama.io.rentacar.business.rules.MaintenanceBusinessRules;
import com.kodlama.io.rentacar.entities.Maintenance;
import com.kodlama.io.rentacar.entities.enums.CarState;
import com.kodlama.io.rentacar.repository.MaintenanceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final CarService carService;
    private final ModelMapper modelMapper;
    private final MaintenanceBusinessRules maintenanceBusinessRules;

    public MaintenanceManager(
            MaintenanceRepository maintenanceRepository,
            CarService carService,
            ModelMapper modelMapper,
            MaintenanceBusinessRules maintenanceBusinessRules
    ) {
        this.maintenanceRepository = maintenanceRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.maintenanceBusinessRules = maintenanceBusinessRules;
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
        maintenanceBusinessRules.checkIfCarIsInMaintenance(createMaintenanceRequest.getCarId());
        maintenanceBusinessRules.checkCarAvailabilityForMaintenance(carService.getById(createMaintenanceRequest.getCarId()).getCarState());

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
        maintenanceBusinessRules.checkIfMaintenanceExistsById(updateMaintenanceRequest.getId());
        Maintenance maintenance = modelMapper.map(updateMaintenanceRequest, Maintenance.class);
        this.maintenanceRepository.save(maintenance);

        UpdateMaintenanceResponse updateMaintenanceResponse =
                modelMapper.map(maintenance, UpdateMaintenanceResponse.class);
        return updateMaintenanceResponse;
    }

    @Override
    public void delete(int id) {
        maintenanceBusinessRules.checkIfMaintenanceExistsById(id);
        makeCarAvailableIfIsCompletedFalse(id);
        this.maintenanceRepository.deleteById(id);
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        maintenanceBusinessRules.checkIfCarIsNotInMaintenance(carId);
        Maintenance maintenance = maintenanceRepository.findByCarIdAndIsCompletedIsFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDate.now());
        maintenanceRepository.save(maintenance);
        carService.changeCarState(carId, CarState.AVAILABLE);
        GetMaintenanceResponse getMaintenanceResponse =
                modelMapper.map(maintenance, GetMaintenanceResponse.class);
        return getMaintenanceResponse;
    }


    private void makeCarAvailableIfIsCompletedFalse(int id) {
        int carId = maintenanceRepository.findById(id).get().getCar().getId();
        if (maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            carService.changeCarState(carId, CarState.AVAILABLE);
        }
    }


}
