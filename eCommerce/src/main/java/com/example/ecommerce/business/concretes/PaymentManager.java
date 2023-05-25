package com.example.ecommerce.business.concretes;

import com.example.ecommerce.adapters.PosService;
import com.example.ecommerce.business.abstracts.PaymentService;
import com.example.ecommerce.business.dto.request.create.CreatePaymentRequest;
import com.example.ecommerce.business.dto.request.update.UpdatePaymentRequest;
import com.example.ecommerce.business.dto.response.create.CreatePaymentResponse;
import com.example.ecommerce.business.dto.response.get.GetAllPaymentsResponse;
import com.example.ecommerce.business.dto.response.get.GetPaymentResponse;
import com.example.ecommerce.business.dto.response.update.UpdatePaymentResponse;
import com.example.ecommerce.common.dto.CreateSalePaymentRequest;
import com.example.ecommerce.entities.concretes.Payment;
import com.example.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;
    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentsResponse> response = payments
                .stream()
                .map(payment -> mapper.map(payment, GetAllPaymentsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse getPaymentResponse = mapper.map(payment, GetPaymentResponse.class);

        return getPaymentResponse;

    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        CreatePaymentResponse response
                = mapper.map(payment, CreatePaymentResponse.class);

        return response;
    }

    @Override
    public UpdatePaymentResponse update(UpdatePaymentRequest request) {
        Payment payment = mapper.map(request, Payment.class);
        repository.save(payment);
        UpdatePaymentResponse response = mapper.map(payment, UpdatePaymentResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void processSalePayment(CreateSalePaymentRequest createRentalPaymentRequest) {
        Payment payment = repository.findByCardNumber(createRentalPaymentRequest.getCardNumber());
        posService.pay();
        payment.setBalance(payment.getBalance() - createRentalPaymentRequest.getPrice());
        repository.save(payment);
    }

}
