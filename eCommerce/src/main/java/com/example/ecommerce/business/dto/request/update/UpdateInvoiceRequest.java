package com.example.ecommerce.business.dto.request.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvoiceRequest {
    private int id;
    @NotBlank
    private String cardHolder;
    @NotBlank
    private Set<String> productName;
    @NotBlank
    private Set<Integer> quantity;
    @NotBlank
    private Set<Double> unitPrice;
    @NotNull
    private double totalPrice;
}