package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.CarService;
import com.kodlama.io.rentacar.business.abstracts.InvoiceService;
import com.kodlama.io.rentacar.business.abstracts.PaymentService;
import com.kodlama.io.rentacar.business.abstracts.RentalService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetRentalResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateRentalResponse;
import com.kodlama.io.rentacar.business.rules.RentalBusinessRules;
import com.kodlama.io.rentacar.common.dto.CreateRentalPaymentRequest;
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
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final RentalBusinessRules rentalBusinessRules;

    public RentalManager(
            RentalRepository rentalRepository,
            CarService carService,
            ModelMapper modelMapper,
            PaymentService paymentService,
            InvoiceService invoiceService,
            RentalBusinessRules rentalBusinessRules
    ){
        this.rentalRepository = rentalRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
        this.rentalBusinessRules = rentalBusinessRules;
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
        rentalBusinessRules.checkIfRentalExistsById(id);
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse getRentalResponse
                = modelMapper.map(rental,GetRentalResponse.class);
        return getRentalResponse;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {
        rentalBusinessRules.checkIfCarInRented(createRentalRequest.getCarId());
        rentalBusinessRules.checkIfCarAvailable(carService.getById(createRentalRequest.getCarId()).getCarState());

        Rental rental = modelMapper.map(createRentalRequest, Rental.class);
        rental.setId(0);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setCompleted(false);
        rental.setStartDate(LocalDate.now());

        CreateRentalPaymentRequest createRentalPaymentRequest = new CreateRentalPaymentRequest();
        modelMapper.map(createRentalRequest.getPaymentRequest(), createRentalPaymentRequest);
        createRentalPaymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(createRentalPaymentRequest);

        rentalRepository.save(rental);
        carService.changeCarState(createRentalRequest.getCarId(), CarState.RENTED);

        CreateInvoiceRequest createInvoiceRequest
                = new CreateInvoiceRequest();
        createInvoiceRequest(createRentalRequest,createInvoiceRequest,rental);
        invoiceService.add(createInvoiceRequest);

        CreateRentalResponse createRentalResponse
                = modelMapper.map(rental, CreateRentalResponse.class);
        return createRentalResponse;
    }

    @Override
    public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {
        rentalBusinessRules.checkIfRentalExistsById(updateRentalRequest.getId());
        Rental rental = modelMapper.map(updateRentalRequest, Rental.class);
        rental.setTotalPrice(getTotalPrice(rental));
        rentalRepository.save(rental);
        UpdateRentalResponse updateRentalResponse
                = modelMapper.map(rental, UpdateRentalResponse.class);
        return updateRentalResponse;
    }

    @Override
    public void delete(int id) {
        rentalBusinessRules.checkIfRentalExistsById(id);
        makeCarAvailableIfIsCompletedFalse(id);
        rentalRepository.deleteById(id);
    }

    @Override
    public GetRentalResponse returnCarFromRented(int carId) {
        rentalBusinessRules.checkIfCarIsNotInRented(carId);
        Rental rental = rentalRepository.findByCarIdAndIsCompletedIsFalse(carId);
        rental.setCompleted(true);
        rentalRepository.save(rental);
        carService.changeCarState(carId, CarState.AVAILABLE);
        GetRentalResponse getRentalResponse =
                modelMapper.map(rental, GetRentalResponse.class);
        return getRentalResponse;

    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

    private void makeCarAvailableIfIsCompletedFalse(int id) {
        int carId = rentalRepository.findById(id).get().getCar().getId();
        if (rentalRepository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            carService.changeCarState(carId, CarState.AVAILABLE);
        }
    }
    private void createInvoiceRequest(
            CreateRentalRequest createRentalRequest,
            CreateInvoiceRequest createInvoiceRequest,
            Rental rental
    ) {
        GetCarResponse getCarResponse = carService.getById(createRentalRequest.getCarId());

        createInvoiceRequest.setModelName(getCarResponse.getModelName());
        createInvoiceRequest.setBrandName(getCarResponse.getModelBrandName());
        createInvoiceRequest.setDailyPrice(createRentalRequest.getDailyPrice());
        createInvoiceRequest.setPlate(getCarResponse.getPlate());
        createInvoiceRequest.setCardHolder(createRentalRequest.getPaymentRequest().getCardHolder());
        createInvoiceRequest.setModelYear(getCarResponse.getModelYear());
        createInvoiceRequest.setRentedForDays(createRentalRequest.getRentedForDays());
        createInvoiceRequest.setRentedAt(rental.getStartDate());
    }
}
