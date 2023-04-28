package com.kodlama.io.rentacar.api.controller;

import com.kodlama.io.rentacar.business.abstracts.MaintenanceService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {
    private final MaintenanceService maintenanceService;

    public MaintenancesController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping()
    public List<GetAllMaintenancesResponse> findAll(){
        return maintenanceService.getAll();
    }
    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable("id") int id){
        return maintenanceService.getById(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@Valid @RequestBody CreateMaintenanceRequest createMaintenanceRequest){
        return maintenanceService.add(createMaintenanceRequest);
    }
    @PutMapping()
    public UpdateMaintenanceResponse update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest){
        return maintenanceService.update(updateMaintenanceRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        maintenanceService.delete(id);
    }
    @PutMapping("/return/{id}")
    public GetMaintenanceResponse returnCarFromMaintenance(@PathVariable("id") int carId){
        return maintenanceService.returnCarFromMaintenance(carId);
    }
}
