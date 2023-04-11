package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.CarService;
import com.kodlama.io.rentacar.business.abstracts.RentalService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetRentalResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateRentalResponse;
import com.kodlama.io.rentacar.entities.Car;
import com.kodlama.io.rentacar.entities.Rental;
import com.kodlama.io.rentacar.entities.enums.CarState;
import com.kodlama.io.rentacar.repository.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final ModelMapper modelMapper;

    public RentalManager(
            RentalRepository rentalRepository,
            CarService carService,
            ModelMapper modelMapper
    ) {
        this.rentalRepository = rentalRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalsResponse> getAllRentalsResponses = rentals
                .stream()
                .map(rental -> modelMapper.map(rental, GetAllRentalsResponse.class))
                .collect(Collectors.toList());
        return getAllRentalsResponses;
    }

    @Override
    public GetRentalResponse getById(int id) {
        checkIfRentalExistsById(id);
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse getRentalResponse
                = modelMapper.map(rental,GetRentalResponse.class);
        return getRentalResponse;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {
        checkIfCarInRented(createRentalRequest.getCarId());
        checkIfCarAvailable(createRentalRequest.getCarId());

        Rental rental = modelMapper.map(createRentalRequest, Rental.class);
        rental.setId(0);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setCompleted(false);
        rental.setStartDate(LocalDate.now());

        rentalRepository.save(rental);
        carService.changeCarState(createRentalRequest.getCarId(), CarState.RENTED);

        CreateRentalResponse createRentalResponse
                = modelMapper.map(rental, CreateRentalResponse.class);
        return createRentalResponse;
    }

    @Override
    public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {
        checkIfRentalExistsById(updateRentalRequest.getId());
        Rental rental = modelMapper.map(updateRentalRequest, Rental.class);
        rental.setTotalPrice(getTotalPrice(rental));
        rentalRepository.save(rental);
        UpdateRentalResponse updateRentalResponse
                = modelMapper.map(rental, UpdateRentalResponse.class);
        return updateRentalResponse;
    }

    @Override
    public void delete(int id) {
        checkIfRentalExistsById(id);
        Rental rental = rentalRepository.findById(id).orElseThrow();
        carService.changeCarState(rental.getCar().getId(), CarState.AVAILABLE);
        rentalRepository.deleteById(id);
    }

    @Override
    public GetRentalResponse returnCarFromRented(int id) {
        checkIfCarIsNotInRented(id);
        Rental rental = rentalRepository.findByCarIdAndIsCompletedIsFalse(id);
        rental.setCompleted(true);
        rentalRepository.save(rental);
        carService.changeCarState(id, CarState.AVAILABLE);
        GetRentalResponse getRentalResponse =
                modelMapper.map(rental, GetRentalResponse.class);
        return getRentalResponse;

    }

    private void checkIfCarAvailable(int id){
       if(!carService.getById(id).getCarState().equals(CarState.AVAILABLE)){
           throw new RuntimeException("Car is not available");
       };
    }

    private void checkIfRentalExistsById(int id) {
        if (!rentalRepository.existsById(id)) {
            throw new RuntimeException("There is no rental information available");
        }
    }
    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }
    private void checkIfCarInRented(int id){
        if(rentalRepository.existsByCarIdAndIsComplatedFalse(id)){
            throw new RuntimeException("This car is in rented");
        }
    }

    private void checkIfCarIsNotInRented(int id) {
        if (!rentalRepository.existsByCarIdAndIsComplatedFalse(id)) {
            throw new RuntimeException("This car is no in rented");
        }
    }
}
