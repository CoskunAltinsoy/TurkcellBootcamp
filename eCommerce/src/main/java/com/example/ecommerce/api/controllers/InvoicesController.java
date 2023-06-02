package com.example.ecommerce.api.controllers;

import com.example.ecommerce.business.abstracts.InvoiceService;
import com.example.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import com.example.ecommerce.business.dto.request.update.UpdateInvoiceRequest;
import com.example.ecommerce.business.dto.response.create.CreateInvoiceResponse;
import com.example.ecommerce.business.dto.response.get.GetAllInvoicesResponse;
import com.example.ecommerce.business.dto.response.get.GetInvoiceResponse;
import com.example.ecommerce.business.dto.response.update.UpdateInvoiceResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping()
    public List<GetAllInvoicesResponse> findAll(){
        return invoiceService.getAll();
    }
    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable("id") int id){
        return invoiceService.getById(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest){
        return invoiceService.add(createInvoiceRequest);
    }
    @PutMapping()
    public UpdateInvoiceResponse update(@RequestBody UpdateInvoiceRequest updateInvoiceRequest){
        return invoiceService.update(updateInvoiceRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        invoiceService.delete(id);
    }
}