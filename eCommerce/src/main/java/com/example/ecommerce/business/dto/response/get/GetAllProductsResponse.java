package com.example.ecommerce.business.dto.response.get;

import com.example.ecommerce.entities.concretes.enums.ProductStockState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsResponse {
    private int id;
    private String name;
    private int quantity;
    private double unitPrice;
    private String description;
    private ProductStockState productStockState;
}
