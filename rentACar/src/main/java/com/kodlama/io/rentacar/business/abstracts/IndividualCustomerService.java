package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.business.dto.requests.AuthRequest;
import com.kodlama.io.rentacar.business.dto.requests.PasswordRequest;
import com.kodlama.io.rentacar.business.dto.requests.ResetPasswordRequest;
import com.kodlama.io.rentacar.business.dto.requests.TokenPasswordRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateIndividualCustomerRequest;
import com.kodlama.io.rentacar.business.dto.responses.AuthResponse;
import com.kodlama.io.rentacar.business.dto.responses.PasswordResponse;
import com.kodlama.io.rentacar.business.dto.responses.ResetPasswordResponse;
import com.kodlama.io.rentacar.business.dto.responses.TokenResetResponse;
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
    public PasswordResponse changePassword(PasswordRequest passwordRequest);
    public ResetPasswordResponse resetPassword(String token, TokenPasswordRequest createTokenPasswordRequest);
    public TokenResetResponse forgotPassword(ResetPasswordRequest createResetPasswordRequest);
}
