package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import com.kodlama.io.rentacar.core.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(UpdatePaymentRequest request);
    void delete(int id);
    void processRentalPayment(CreateRentalPaymentRequest request);
}