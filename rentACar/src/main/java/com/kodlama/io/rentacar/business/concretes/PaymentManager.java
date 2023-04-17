package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.adapters.PosService;
import com.kodlama.io.rentacar.business.abstracts.PaymentService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.kodlama.io.rentacar.common.dto.CreateRentalPaymentRequest;
import com.kodlama.io.rentacar.entities.Payment;
import com.kodlama.io.rentacar.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentManager implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final PosService posService;

    public PaymentManager(
            PaymentRepository paymentRepository,
            ModelMapper modelMapper,
            PosService posService
    ) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
        this.posService = posService;
    }

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        List<GetAllPaymentsResponse> getAllPaymentsResponses = payments
                .stream()
                .map(payment -> modelMapper.map(payment, GetAllPaymentsResponse.class))
                .toList();

        return getAllPaymentsResponses;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        checkIfPaymentExists(id);
        Payment payment = paymentRepository.findById(id).orElseThrow();
        GetPaymentResponse getPaymentResponse = modelMapper.map(payment, GetPaymentResponse.class);

        return getPaymentResponse;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        checkIfCardExists(createPaymentRequest);
        Payment payment = modelMapper.map(createPaymentRequest, Payment.class);
        payment.setId(0);
        paymentRepository.save(payment);
        CreatePaymentResponse createPaymentResponse
                = modelMapper.map(payment, CreatePaymentResponse.class);

        return createPaymentResponse;
    }

    @Override
    public UpdatePaymentResponse update(UpdatePaymentRequest updatePaymentRequest) {
        checkIfPaymentExists(updatePaymentRequest.getId());
        Payment payment = modelMapper.map(updatePaymentRequest, Payment.class);
        paymentRepository.save(payment);
        UpdatePaymentResponse updatePaymentResponse = modelMapper.map(payment, UpdatePaymentResponse.class);

        return updatePaymentResponse;
    }

    @Override
    public void delete(int id) {
        checkIfPaymentExists(id);
        paymentRepository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest createRentalPaymentRequest) {
        checkIfPaymentIsValid(createRentalPaymentRequest);
        Payment payment = paymentRepository.findByCardNumber(createRentalPaymentRequest.getCardNumber());
        checkIfBalanceIsEnough(createRentalPaymentRequest.getPrice(), payment.getBalance());
        posService.pay();
        payment.setBalance(payment.getBalance() - createRentalPaymentRequest.getPrice());
        paymentRepository.save(payment);
    }
    private void checkIfPaymentExists(int id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("There is no payment information found");
        }
    }

    private void checkIfBalanceIsEnough(double price, double balance) {
        if (balance < price) {
            throw new RuntimeException("Insufficient balance.");
        }
    }

    private void checkIfCardExists(CreatePaymentRequest createPaymentRequest) {
        if (paymentRepository.existsByCardNumber(createPaymentRequest.getCardNumber())) {
            throw new RuntimeException("The card number is already registered.");
        }
    }

    private void checkIfPaymentIsValid(CreateRentalPaymentRequest createRentalPaymentRequest) {
        if (!paymentRepository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                createRentalPaymentRequest.getCardNumber(),
                createRentalPaymentRequest.getCardHolder(),
                createRentalPaymentRequest.getCardExpirationYear(),
                createRentalPaymentRequest.getCardExpirationMonth(),
                createRentalPaymentRequest.getCardCvv()
        )) {
            throw new RuntimeException("Your card details are incorrect.");
        }
    }
}
