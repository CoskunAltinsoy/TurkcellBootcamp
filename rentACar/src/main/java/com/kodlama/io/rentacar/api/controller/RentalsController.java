package com.kodlama.io.rentacar.api.controller;

import com.kodlama.io.rentacar.business.abstracts.RentalService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.*;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateRentalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
    private final RentalService rentalService;
    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    @GetMapping()
    public List<GetAllRentalsResponse> findAll(){
        return rentalService.getAll();
    }
    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable("id") int id){
        return rentalService.getById(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentalResponse add(@RequestBody CreateRentalRequest createRentalRequest){
        return rentalService.add(createRentalRequest);
    }
    @PutMapping()
    public UpdateRentalResponse update(@RequestBody UpdateRentalRequest updateRentalRequest){
        return rentalService.update(updateRentalRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        rentalService.delete(id);
    }
    @PutMapping("/return/{id}")
    public GetRentalResponse returnCarFromRented(@PathVariable("id") int carId){
        return rentalService.returnCarFromRented(carId);
    }
}
