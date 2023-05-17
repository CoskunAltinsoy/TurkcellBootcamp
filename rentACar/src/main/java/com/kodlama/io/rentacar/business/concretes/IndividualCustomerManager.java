package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.IndividualCustomerService;
import com.kodlama.io.rentacar.business.abstracts.UserService;
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
import com.kodlama.io.rentacar.business.rules.IndividualCustomerBusinessRules;
import com.kodlama.io.rentacar.business.rules.UserBusinessRules;
import com.kodlama.io.rentacar.core.exceptions.BusinessException;
import com.kodlama.io.rentacar.core.util.email.EmailService;
import com.kodlama.io.rentacar.core.util.passwordToken.TokenGenerator;
import com.kodlama.io.rentacar.entities.IndividualCustomer;
import com.kodlama.io.rentacar.entities.User;
import com.kodlama.io.rentacar.repository.IndividualCustomerRepository;
import com.kodlama.io.rentacar.security.CustomUserDetail;
import com.kodlama.io.rentacar.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;
    private final EmailService emailService;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private final UserBusinessRules userBusinessRules;

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
        userBusinessRules.checkIfUserExistsById(id);
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).orElseThrow();
        GetIndividualCustomerResponse getIndividualCustomerResponse =
                modelMapper.map(individualCustomer, GetIndividualCustomerResponse.class);

        return getIndividualCustomerResponse;
    }

    @Override
    public List<GetAllIndividualCustomerResponse> getAll() {
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAll();
        List<GetAllIndividualCustomerResponse> getAllIndividualCustomerResponses = individualCustomers
                .stream()
                .map(individualCustomer -> modelMapper.map(individualCustomer, GetAllIndividualCustomerResponse.class))
                .collect(Collectors.toList());

        return getAllIndividualCustomerResponses;
    }

    @Override
    public void delete(int id) {
        userBusinessRules.checkIfUserExistsById(id);
        individualCustomerRepository.deleteById(id);
    }

    @Override
    public PasswordResponse changePassword(PasswordRequest passwordRequest) {
        User user = userService.getByEmail(passwordRequest.getEmail());

        individualCustomerBusinessRules.checkPasswords(passwordRequest.getOldPassword(),
                user.getPassword());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String encodedNewPassword = bCryptPasswordEncoder.encode(passwordRequest.getNewPassword());
        user.setPassword(encodedNewPassword);

        userService.add(user);

        PasswordResponse passwordResponse = modelMapper.map(passwordRequest, PasswordResponse.class);

        return passwordResponse;
    }

    @Override
    public ResetPasswordResponse resetPassword(String token, TokenPasswordRequest tokenPasswordRequest) {
        User user = userService.getByResetToken(token);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        user.setPassword(bCryptPasswordEncoder.encode(tokenPasswordRequest.getPassword()));
        user.setResetToken(null);

        userService.add(user);

        ResetPasswordResponse resetPasswordResponse =
                modelMapper.map(user, ResetPasswordResponse.class);

        return resetPasswordResponse;
    }

    @Override
    public TokenResetResponse forgotPassword(ResetPasswordRequest createResetPasswordRequest) {
        User user = userService.getByEmail(createResetPasswordRequest.getEmail());

        user.setResetToken(tokenGenerator.generateToken());

        userService.add(user);

        TokenResetResponse tokenResetResponse = new TokenResetResponse();
        tokenResetResponse.setUrlWithToken("http://localhost:8080/api/individualcustomers/resetPassword?token=" +
                user.getResetToken());

        emailService.sendEmail(user.getEmail(), tokenResetResponse.getUrlWithToken());

        return tokenResetResponse;
    }

}
