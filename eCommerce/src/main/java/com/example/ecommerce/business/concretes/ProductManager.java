package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.business.dto.request.create.CreateProductRequest;
import com.example.ecommerce.business.dto.request.update.UpdateProductRequest;
import com.example.ecommerce.business.dto.response.create.CreateProductResponse;
import com.example.ecommerce.business.dto.response.get.GetAllProductsResponse;
import com.example.ecommerce.business.dto.response.get.GetProductResponse;
import com.example.ecommerce.business.dto.response.update.UpdateProductResponse;
import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.entities.concretes.enums.ProductStockState;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public CreateProductResponse add(CreateProductRequest createProductRequest) {
        Product product = modelMapper.map(createProductRequest, Product.class);
        product.setId(0);
        productRepository.save(product);

        CreateProductResponse createProductResponse =
                modelMapper.map(product, CreateProductResponse.class);

       return createProductResponse;
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest updateProductRequest) {
        Product product = modelMapper.map(updateProductRequest, Product.class);
        productRepository.save(product);

        UpdateProductResponse updateProductResponse =
                modelMapper.map(product, UpdateProductResponse.class);

        return updateProductResponse;
    }

    @Override
    public List<GetAllProductsResponse> getAll(boolean includeAvailable) {
        List<Product> products = filterProductsByProductState(includeAvailable);
        List<GetAllProductsResponse> getAllProductsRespons = products
                .stream()
                .map(product -> modelMapper.map(product, GetAllProductsResponse.class))
                .collect(Collectors.toList());

        return getAllProductsRespons;
    }

    @Override
    public GetProductResponse getById(int id) {
        Product product = productRepository.findById(id).orElseThrow();
        GetProductResponse getProductResponse =
                modelMapper.map(product, GetProductResponse.class);

        return getProductResponse;
    }

    @Override
    public void changeState(int carId, ProductStockState productStockState) {
        Product product = productRepository.findById(carId).orElseThrow();
        product.setProductStockState(productStockState);
        productRepository.save(product);
    }
    private List<Product> filterProductsByProductState(boolean includeAvailable){
        if (includeAvailable){
            return productRepository.findAll();
        }
        return productRepository.findAllByProductStateIsNot(ProductStockState.AVAILABLE);
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
