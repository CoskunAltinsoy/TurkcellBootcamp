package com.kodlama.io.rentacar.api.controller;

import com.kodlama.io.rentacar.business.abstracts.InvoiceService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllInvoicesResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateInvoiceResponse;
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
