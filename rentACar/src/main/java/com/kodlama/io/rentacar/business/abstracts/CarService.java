package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.kodlama.io.rentacar.entities.enums.CarState;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAll(boolean isMaintenanceIncluded);
    GetCarResponse getById(int id);
    CreateCarResponse add(CreateCarRequest createCarRequest);
    UpdateCarResponse update(UpdateCarRequest updateCarRequest);
    void delete(int id);
    void changeCarState(int id, CarState carState);
}
