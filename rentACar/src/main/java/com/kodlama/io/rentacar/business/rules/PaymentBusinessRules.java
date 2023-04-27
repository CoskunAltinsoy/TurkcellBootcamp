package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.business.dto.requests.create.CreatePaymentRequest;
import com.kodlama.io.rentacar.common.dto.CreateRentalPaymentRequest;
import com.kodlama.io.rentacar.core.exceptions.BusinessException;
import com.kodlama.io.rentacar.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentBusinessRules {
    private final PaymentRepository paymentRepository;

    public PaymentBusinessRules(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    public void checkIfPaymentExists(int id) {
        if (!paymentRepository.existsById(id)) {
            throw new BusinessException("There is no payment information found");
        }
    }

    public void checkIfBalanceIsEnough(double price, double balance) {
        if (balance < price) {
            throw new BusinessException("Insufficient balance.");
        }
    }

    public void checkIfCardExists(CreatePaymentRequest createPaymentRequest) {
        if (paymentRepository.existsByCardNumber(createPaymentRequest.getCardNumber())) {
            throw new BusinessException("The card number is already registered.");
        }
    }

    public void checkIfPaymentIsValid(CreateRentalPaymentRequest createRentalPaymentRequest) {
        if (!paymentRepository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                createRentalPaymentRequest.getCardNumber(),
                createRentalPaymentRequest.getCardHolder(),
                createRentalPaymentRequest.getCardExpirationYear(),
                createRentalPaymentRequest.getCardExpirationMonth(),
                createRentalPaymentRequest.getCardCvv()
        )) {
            throw new RuntimeException("Your card details are incorrect.");
        }
    }
}
