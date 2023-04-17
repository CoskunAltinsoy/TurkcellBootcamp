package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceBusinessRules {
    private final InvoiceRepository invoiceRepository;

    public InvoiceBusinessRules(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void checkIfInvoiceExists(int id) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("");
        }
    }
}
