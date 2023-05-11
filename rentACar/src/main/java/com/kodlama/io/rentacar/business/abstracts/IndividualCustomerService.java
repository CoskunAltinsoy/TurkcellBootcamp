package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.business.dto.requests.AuthRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateIndividualCustomerRequest;
import com.kodlama.io.rentacar.business.dto.responses.AuthResponse;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateIndividualCustomerResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllIndividualCustomerResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    public AuthResponse login(AuthRequest authRequest);
    public CreateIndividualCustomerResponse register(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    public GetIndividualCustomerResponse getById(int id);
    public List<GetAllIndividualCustomerResponse> getAll();
    public void delete(int id);
}
