package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository invoiceRepository;

    public void checkIfInvoiceExists(int id) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("");
        }
    }
}
