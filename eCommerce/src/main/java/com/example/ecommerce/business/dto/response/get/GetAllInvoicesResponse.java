package com.example.ecommerce.business.dto.response.get;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoicesResponse {

    private int id;
    @NotBlank
    private String cardHolder;
    @NotBlank
    private String productName;
    @NotNull
    private int quantity;
    @NotNull
    private double unitPrice;
    @NotNull
    private double totalPrice;
}