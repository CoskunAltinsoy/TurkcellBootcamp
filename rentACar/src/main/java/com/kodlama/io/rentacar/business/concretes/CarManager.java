package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.CarService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.kodlama.io.rentacar.entities.Car;
import com.kodlama.io.rentacar.entities.enums.CarState;
import com.kodlama.io.rentacar.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarManager(CarRepository carRepository,
                      ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GetAllCarsResponse> getAll(boolean isMaintenanceIncluded) {
        List<Car> cars = filterCarsByMaintenanceState(isMaintenanceIncluded);
        List<GetAllCarsResponse> getAllCarsResponses = cars
                .stream()
                .map(car -> modelMapper.map(car, GetAllCarsResponse.class))
                .collect(Collectors.toList());
        return getAllCarsResponses;
    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExistsById(id);
        Car car = this.carRepository.findById(id).orElseThrow();
        GetCarResponse getCarResponse = modelMapper.map(car, GetCarResponse.class);

        return getCarResponse;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest createCarRequest) {
        Car car = modelMapper.map(createCarRequest, Car.class);
        car.setId(0);
        car.setCarState(CarState.AVAILABLE);
        this.carRepository.save(car);

        CreateCarResponse createCarResponse = modelMapper.map(car, CreateCarResponse.class);
        return createCarResponse;
    }

    @Override
    public UpdateCarResponse update(UpdateCarRequest updateCarRequest) {
        checkIfCarExistsById(updateCarRequest.getId());
        Car car = modelMapper.map(updateCarRequest, Car.class);
        this.carRepository.save(car);

        UpdateCarResponse updateCarResponse = modelMapper.map(car, UpdateCarResponse.class);
        return updateCarResponse;
    }

    @Override
    public void delete(int id) {
        checkIfCarExistsById(id);
        this.carRepository.deleteById(id);
    }

    @Override
    public void changeCarState(int id, CarState carState) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setCarState(carState);
        carRepository.save(car);
    }

    private void checkIfCarExistsById(int id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("This car is not registered in the system");
        }
    }
    private List<Car> filterCarsByMaintenanceState(boolean isMaintenanceIncluded) {
        if(isMaintenanceIncluded){
            return carRepository.findAllByCarStateIsNot(CarState.MAINTENANCE);
        }
        return carRepository.findAll();
    }

}
