package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.IndividualCustomerService;
import com.kodlama.io.rentacar.business.dto.requests.AuthRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateIndividualCustomerRequest;
import com.kodlama.io.rentacar.business.dto.responses.AuthResponse;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateIndividualCustomerResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllIndividualCustomerResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetIndividualCustomerResponse;
import com.kodlama.io.rentacar.entities.IndividualCustomer;
import com.kodlama.io.rentacar.repository.IndividualCustomerRepository;
import com.kodlama.io.rentacar.security.CustomUserDetail;
import com.kodlama.io.rentacar.security.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository,
                                     ModelMapper modelMapper,
                                     AuthenticationManager authenticationManager,
                                     JwtUtils jwtUtils,
                                     PasswordEncoder passwordEncoder) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication =
                authenticationManager.authenticate
                        (new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();

        return new AuthResponse(jwt);
    }

    @Override
    public CreateIndividualCustomerResponse register
            (CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        IndividualCustomer individualCustomer =
                modelMapper.map(createIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomer.setId(0);
        individualCustomer.setPassword(passwordEncoder.encode(createIndividualCustomerRequest.getPassword()));
        individualCustomerRepository.save(individualCustomer);

        CreateIndividualCustomerResponse createIndividualCustomerResponse =
                modelMapper.map(individualCustomer, CreateIndividualCustomerResponse.class);

        return createIndividualCustomerResponse;
    }

    @Override
    public GetIndividualCustomerResponse getById(int id) {
        return null;
    }

    @Override
    public List<GetAllIndividualCustomerResponse> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
