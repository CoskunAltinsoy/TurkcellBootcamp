package com.example.ecommerce.repository.concretes;

import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products;

    public InMemoryProductRepository(List<Product> products) {
        this.products = products;
        products.add(new Product(1,"Iphone 12",10,30000d,"Telefon"));
        products.add(new Product(2,"Samsung S22",10,28000d,"Telefon"));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getById(int id) {

        Product getProduct = null;
        for (Product product : products) {
            if (product.getId() == id) {
                getProduct = product;
            }
        }
        return getProduct;
    }

    @Override
    public Product add(Product product) {
       products.add(product);
       return product;
    }

    @Override
    public Product update(Product product) {
        Product updateProduct = getById(product.getId());
        updateProduct.setName(product.getName());
        updateProduct.setQuantity(product.getQuantity());
        updateProduct.setUnitPrice(product.getUnitPrice());
        updateProduct.setDescription(product.getDescription());
        return  updateProduct;
    }

    @Override
    public void delete(int id) {
        products.remove(getById(id));
    }
}
