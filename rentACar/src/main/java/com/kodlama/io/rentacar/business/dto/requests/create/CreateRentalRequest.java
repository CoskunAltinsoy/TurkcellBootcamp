package com.kodlama.io.rentacar.business.dto.requests.create;

import com.kodlama.io.rentacar.business.dto.requests.PaymentRequest;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
    @Min(0)
    private int carId;
    @Min(1)
    private double dailyPrice;
    @Min(1)
    private int rentedForDays;
    private PaymentRequest paymentRequest;
}
