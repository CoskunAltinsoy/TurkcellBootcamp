package com.example.ecommerce.business.dto.request.create;

import com.example.ecommerce.business.dto.request.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private String name;
    private int quantity;
    private double unitPrice;
    private String description;
    private List<CategoryRequest> categories;
}
