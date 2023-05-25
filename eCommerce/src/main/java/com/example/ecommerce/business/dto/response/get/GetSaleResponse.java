package com.example.ecommerce.business.dto.response.get;

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
public class GetSaleResponse {
    private int id;
    private double totalPrice;
    private List<ProductRequest> products;
}
