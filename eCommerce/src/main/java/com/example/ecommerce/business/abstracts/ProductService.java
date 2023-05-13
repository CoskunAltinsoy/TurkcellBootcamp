package com.example.ecommerce.business.abstracts;

import com.example.ecommerce.business.dto.request.create.CreateProductRequest;
import com.example.ecommerce.business.dto.request.update.UpdateProductRequest;
import com.example.ecommerce.business.dto.response.create.CreateProductResponse;
import com.example.ecommerce.business.dto.response.get.GetAllProductResponse;
import com.example.ecommerce.business.dto.response.get.GetProductResponse;
import com.example.ecommerce.business.dto.response.update.UpdateProductResponse;
import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.entities.concretes.enums.ProductState;

import java.util.List;

public interface ProductService {
    CreateProductResponse add(CreateProductRequest createProductRequest);
    void delete(int id);
    UpdateProductResponse update(UpdateProductRequest updateProductRequest);
    List<GetAllProductResponse> getAll();
    GetProductResponse getById(int id);
    void changeState(int carId, ProductState productState);
}
