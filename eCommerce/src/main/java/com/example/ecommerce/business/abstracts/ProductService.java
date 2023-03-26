package com.example.ecommerce.business.abstracts;

import com.example.ecommerce.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    Product add(Product product);
    void delete(int id);
    Product update(Product product);
    List<Product> getAll();
    Product getById(int id);
}
