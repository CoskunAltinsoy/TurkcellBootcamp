package com.example.ecommerce.business.dto.request.update;

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
public class UpdateSaleRequest {
    private int id;
    private List<ProductRequest> products;
}
