package com.kodlama.io.rentacar.api.controller;

import com.kodlama.io.rentacar.business.abstracts.IndividualCustomerService;
import com.kodlama.io.rentacar.business.dto.requests.AuthRequest;
import com.kodlama.io.rentacar.business.dto.requests.PasswordRequest;
import com.kodlama.io.rentacar.business.dto.requests.ResetPasswordRequest;
import com.kodlama.io.rentacar.business.dto.requests.TokenPasswordRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateIndividualCustomerRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.responses.AuthResponse;
import com.kodlama.io.rentacar.business.dto.responses.PasswordResponse;
import com.kodlama.io.rentacar.business.dto.responses.ResetPasswordResponse;
import com.kodlama.io.rentacar.business.dto.responses.TokenResetResponse;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateIndividualCustomerResponse;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllIndividualCustomerResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetIndividualCustomerResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateInvoiceResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersController {
    private final IndividualCustomerService individualCustomerService;

    public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }
    @GetMapping()
    public List<GetAllIndividualCustomerResponse> findAll(){
        return individualCustomerService.getAll();
    }
    @GetMapping("/{id}")
    public GetIndividualCustomerResponse getById(@PathVariable("id") int id){
        return individualCustomerService.getById(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateIndividualCustomerResponse register(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest){
        return individualCustomerService.register(createIndividualCustomerRequest);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest){
        return individualCustomerService.login(authRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        individualCustomerService.delete(id);
    }
    @PostMapping("/changePassword")
    public PasswordResponse changePassword(
            @RequestBody PasswordRequest createPasswordRequest){
        return individualCustomerService.changePassword(createPasswordRequest);
    }
    @PostMapping("/forgotPassword")
    public TokenResetResponse forgotPassword(
            @RequestBody ResetPasswordRequest createResetPasswordRequest){
        return individualCustomerService.forgotPassword(createResetPasswordRequest);
    } @PutMapping("/resetPassword")
    public ResetPasswordResponse resetPassword(@RequestParam String token,
                                               @RequestBody TokenPasswordRequest createTokenPasswordRequest){
        return individualCustomerService.resetPassword(token, createTokenPasswordRequest);
    }
}
