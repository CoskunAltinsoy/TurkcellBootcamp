package com.example.ecommerce.business.abstracts;

import com.example.ecommerce.business.dto.request.create.CreatePaymentRequest;
import com.example.ecommerce.business.dto.request.update.UpdatePaymentRequest;
import com.example.ecommerce.business.dto.response.create.CreatePaymentResponse;
import com.example.ecommerce.business.dto.response.get.GetAllPaymentsResponse;
import com.example.ecommerce.business.dto.response.get.GetPaymentResponse;
import com.example.ecommerce.business.dto.response.update.UpdatePaymentResponse;
import com.example.ecommerce.common.dto.CreateSalePaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(UpdatePaymentRequest request);
    void delete(int id);
    void processSalePayment(CreateSalePaymentRequest request);
}