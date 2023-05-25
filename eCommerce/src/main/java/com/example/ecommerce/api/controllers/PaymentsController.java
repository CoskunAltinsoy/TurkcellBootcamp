package com.example.ecommerce.api.controllers;

import com.example.ecommerce.business.abstracts.PaymentService;
import com.example.ecommerce.business.dto.request.create.CreatePaymentRequest;
import com.example.ecommerce.business.dto.request.update.UpdatePaymentRequest;
import com.example.ecommerce.business.dto.response.create.CreatePaymentResponse;
import com.example.ecommerce.business.dto.response.get.GetAllPaymentsResponse;
import com.example.ecommerce.business.dto.response.get.GetPaymentResponse;
import com.example.ecommerce.business.dto.response.update.UpdatePaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable int id,
                                        @Valid @RequestBody UpdatePaymentRequest request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}