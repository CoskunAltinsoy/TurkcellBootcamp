package com.example.ecommerce.business.abstracts;

import com.example.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import com.example.ecommerce.business.dto.request.update.UpdateInvoiceRequest;
import com.example.ecommerce.business.dto.response.create.CreateInvoiceResponse;
import com.example.ecommerce.business.dto.response.get.GetAllInvoicesResponse;
import com.example.ecommerce.business.dto.response.get.GetInvoiceResponse;
import com.example.ecommerce.business.dto.response.update.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(int id);
    CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);
    UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest);
    void delete(int id);
}