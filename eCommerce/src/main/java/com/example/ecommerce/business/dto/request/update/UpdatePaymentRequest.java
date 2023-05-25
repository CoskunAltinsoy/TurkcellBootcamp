package com.example.ecommerce.business.dto.request.update;

import com.example.ecommerce.business.dto.request.PaymentRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentRequest extends PaymentRequest {

    private int id;

    @NotNull
    @Min(value = 1)
    private double balance;

}