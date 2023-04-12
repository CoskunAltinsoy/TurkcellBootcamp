package com.kodlama.io.rentacar.business.abstracts;



import com.kodlama.io.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetRentalResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(int id);
    CreateRentalResponse add(CreateRentalRequest createRentalRequest);
    UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest);
    void delete(int id);
    GetRentalResponse returnCarFromRented(int carId);
}
