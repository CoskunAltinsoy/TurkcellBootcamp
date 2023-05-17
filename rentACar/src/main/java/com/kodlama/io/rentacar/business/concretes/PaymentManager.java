package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.adapters.PosService;
import com.kodlama.io.rentacar.business.abstracts.PaymentService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.kodlama.io.rentacar.business.rules.PaymentBusinessRules;
import com.kodlama.io.rentacar.common.dto.CreateRentalPaymentRequest;
import com.kodlama.io.rentacar.entities.Payment;
import com.kodlama.io.rentacar.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final PosService posService;
    private final PaymentBusinessRules paymentBusinessRules;

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
        paymentBusinessRules.checkIfPaymentExists(id);
        Payment payment = paymentRepository.findById(id).orElseThrow();
        GetPaymentResponse getPaymentResponse = modelMapper.map(payment, GetPaymentResponse.class);

        return getPaymentResponse;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        paymentBusinessRules.checkIfCardExists(createPaymentRequest);
        Payment payment = modelMapper.map(createPaymentRequest, Payment.class);
        payment.setId(0);
        paymentRepository.save(payment);
        CreatePaymentResponse createPaymentResponse
                = modelMapper.map(payment, CreatePaymentResponse.class);

        return createPaymentResponse;
    }

    @Override
    public UpdatePaymentResponse update(UpdatePaymentRequest updatePaymentRequest) {
        paymentBusinessRules.checkIfPaymentExists(updatePaymentRequest.getId());
        Payment payment = modelMapper.map(updatePaymentRequest, Payment.class);
        paymentRepository.save(payment);
        UpdatePaymentResponse updatePaymentResponse = modelMapper.map(payment, UpdatePaymentResponse.class);

        return updatePaymentResponse;
    }

    @Override
    public void delete(int id) {
        paymentBusinessRules.checkIfPaymentExists(id);
        paymentRepository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest createRentalPaymentRequest) {
        paymentBusinessRules.checkIfPaymentIsValid(createRentalPaymentRequest);
        Payment payment = paymentRepository.findByCardNumber(createRentalPaymentRequest.getCardNumber());
        paymentBusinessRules.checkIfBalanceIsEnough(createRentalPaymentRequest.getPrice(), payment.getBalance());
        posService.pay();
        payment.setBalance(payment.getBalance() - createRentalPaymentRequest.getPrice());
        paymentRepository.save(payment);
    }

}
