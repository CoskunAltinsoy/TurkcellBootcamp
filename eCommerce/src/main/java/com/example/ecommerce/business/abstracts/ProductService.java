package com.example.ecommerce.business.abstracts;

import com.example.ecommerce.business.dto.request.create.CreateProductRequest;
import com.example.ecommerce.business.dto.request.update.UpdateProductRequest;
import com.example.ecommerce.business.dto.response.create.CreateProductResponse;
import com.example.ecommerce.business.dto.response.get.GetAllProductsResponse;
import com.example.ecommerce.business.dto.response.get.GetProductResponse;
import com.example.ecommerce.business.dto.response.update.UpdateProductResponse;
import com.example.ecommerce.entities.concretes.enums.ProductState;

import java.util.List;

public interface ProductService {
    CreateProductResponse add(CreateProductRequest request);
    void delete(int id);
    UpdateProductResponse update(UpdateProductRequest request);
    List<GetAllProductsResponse> getAll(boolean includeAvailable);
    GetProductResponse getById(int id);
    void changeProductState(int carId, ProductState state);

}
