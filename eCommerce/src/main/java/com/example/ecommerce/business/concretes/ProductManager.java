package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product add(Product product) {
        if (checkProduct(product)){
            productRepository.add(product);
        }
        return product;
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public Product update(Product product) {
        if (checkProduct(product)){
            productRepository.update(product);
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }

    private boolean checkProduct(Product product){
        if(product.getUnitPrice()>0 &&
                product.getQuantity()>0 &&
                    product.getDescription().length() < 50 &&
                        product.getDescription().length() > 10){
            return true;
        }
        return false;
    }
}
