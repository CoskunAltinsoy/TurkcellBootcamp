package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.PaymentService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.kodlama.io.rentacar.core.dto.CreateRentalPaymentRequest;
import com.kodlama.io.rentacar.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentManager implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentManager(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        return null;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        return null;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        return null;
    }

    @Override
    public UpdatePaymentResponse update(UpdatePaymentRequest request) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest request) {

    }
}
