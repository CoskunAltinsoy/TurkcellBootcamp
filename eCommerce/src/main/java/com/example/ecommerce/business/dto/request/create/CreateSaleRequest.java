package com.example.ecommerce.business.dto.request.create;

import com.example.ecommerce.business.dto.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSaleRequest {
    private List<ProductRequest> products;
    private int quantity;
}
