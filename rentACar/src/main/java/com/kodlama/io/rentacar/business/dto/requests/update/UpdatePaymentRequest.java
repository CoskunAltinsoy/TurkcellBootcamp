package com.kodlama.io.rentacar.business.dto.requests.update;

import com.kodlama.io.rentacar.business.dto.requests.PaymentRequest;
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
