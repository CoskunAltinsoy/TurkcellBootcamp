package com.kodlama.io.rentacar.api.controller;

import com.kodlama.io.rentacar.business.abstracts.PaymentService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreatePaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllPaymentsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetPaymentResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;

    public PaymentsController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public List<GetAllPaymentsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
        return service.add(createPaymentRequest);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable int id,
                                        @Valid @RequestBody UpdatePaymentRequest updatePaymentRequest) {
        return service.update(updatePaymentRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
