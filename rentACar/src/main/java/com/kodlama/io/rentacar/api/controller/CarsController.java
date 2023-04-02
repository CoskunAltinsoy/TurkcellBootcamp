package com.kodlama.io.rentacar.api.controller;

import com.kodlama.io.rentacar.business.abstracts.CarService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping()
    public List<GetAllCarsResponse> getAll(@RequestParam(required = false) boolean maintenaces){
        return carService.getAll(maintenaces);
    }
    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable("id") int id){
        return carService.getById(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@RequestBody CreateCarRequest createCarRequest){
        return carService.add(createCarRequest);
    }
    @PutMapping()
    public UpdateCarResponse update(@RequestBody UpdateCarRequest updateCarRequest){
        return carService.update(updateCarRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        carService.delete(id);
    }
}
