package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.InvoiceService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllInvoicesResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateInvoiceResponse;
import com.kodlama.io.rentacar.business.rules.InvoiceBusinessRules;
import com.kodlama.io.rentacar.entities.Invoice;
import com.kodlama.io.rentacar.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;
    private final InvoiceBusinessRules invoiceBusinessRules;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<GetAllInvoicesResponse> getAllInvoicesResponses = invoices
                .stream()
                .map(invoice -> modelMapper.map(invoice, GetAllInvoicesResponse.class))
                .collect(Collectors.toList());
        return getAllInvoicesResponses;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        invoiceBusinessRules.checkIfInvoiceExists(id);
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        GetInvoiceResponse getInvoiceResponse = modelMapper.map(invoice, GetInvoiceResponse.class);

        return getInvoiceResponse;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice = modelMapper.map(createInvoiceRequest, Invoice.class);
        invoice.setId(0);
        invoice.setRentedAt(LocalDate.now());
        invoice.setTotalPrice(getTotalPrice(invoice));
        invoiceRepository.save(invoice);

        CreateInvoiceResponse createInvoiceResponse =
                modelMapper.map(invoice, CreateInvoiceResponse.class);
        return createInvoiceResponse;
    }

    @Override
    public UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest) {
        invoiceBusinessRules.checkIfInvoiceExists(updateInvoiceRequest.getId());
        Invoice invoice = modelMapper.map(updateInvoiceRequest, Invoice.class);
        invoice.setTotalPrice(getTotalPrice(invoice));
        invoiceRepository.save(invoice);

        UpdateInvoiceResponse updateInvoiceResponse =
                modelMapper.map(invoice, UpdateInvoiceResponse.class);
        return updateInvoiceResponse;
    }

    @Override
    public void delete(int id) {
        invoiceBusinessRules.checkIfInvoiceExists(id);
        invoiceRepository.deleteById(id);
    }

    private double getTotalPrice(Invoice invoice) {
        return invoice.getDailyPrice() * invoice.getRentedForDays();
    }

}
