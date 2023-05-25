package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.CategoryService;
import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.business.dto.request.create.CreateProductRequest;
import com.example.ecommerce.business.dto.request.update.UpdateProductRequest;
import com.example.ecommerce.business.dto.response.create.CreateProductResponse;
import com.example.ecommerce.business.dto.response.get.GetAllProductsResponse;
import com.example.ecommerce.business.dto.response.get.GetProductResponse;
import com.example.ecommerce.business.dto.response.update.UpdateProductResponse;
import com.example.ecommerce.entities.concretes.Category;
import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.entities.concretes.enums.ProductState;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;
    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        Product product = mapper.map(request, Product.class);
        product.setId(0);
        product.setProductState(ProductState.ACTIVE);

        Set<Category> categories = request.getCategories().stream()
                .map(category -> mapper.map(categoryService.getById(category.getId()), Category.class))
                .collect(Collectors.toSet());

        product.setCategories(categories);
        repository.save(product);

        CreateProductResponse response =
                mapper.map(product, CreateProductResponse.class);

       return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest request) {
        Product product = mapper.map(request, Product.class);

        Set<Category> categories = request.getCategories().stream()
                .map(category -> mapper.map(categoryService.getById(category.getId()), Category.class))
                .collect(Collectors.toSet());

        product.setCategories(categories);
        repository.save(product);

        UpdateProductResponse response =
                mapper.map(product, UpdateProductResponse.class);

        return response;
    }

    @Override
    public List<GetAllProductsResponse> getAll(boolean includeAvailable) {
        List<Product> products = filterProductsByProductState(includeAvailable);
        List<GetAllProductsResponse> responses = products
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetProductResponse getById(int id) {
        Product product = repository.findById(id).orElseThrow();
        GetProductResponse response =
                mapper.map(product, GetProductResponse.class);

        return response;
    }
    @Override
    public void changeProductState(int productId, ProductState state) {
        Product product = repository.findById(productId).orElseThrow();
        product.setProductState(state);
        repository.save(product);
    }

    private List<Product> filterProductsByProductState(boolean includeAvailable){
        if (includeAvailable){
            return repository.findAll();
        }
        return repository.findAllByProductStateIsNot(ProductState.ACTIVE);
    }


}
