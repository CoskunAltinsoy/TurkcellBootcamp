package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.business.dto.request.create.CreateProductRequest;
import com.example.ecommerce.business.dto.request.update.UpdateProductRequest;
import com.example.ecommerce.business.dto.response.create.CreateProductResponse;
import com.example.ecommerce.business.dto.response.get.GetAllProductResponse;
import com.example.ecommerce.business.dto.response.get.GetProductResponse;
import com.example.ecommerce.business.dto.response.update.UpdateProductResponse;
import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.entities.concretes.enums.ProductState;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

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
    public List<GetAllProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<GetAllProductResponse> getAllProductResponses = products
                .stream()
                .map(product -> modelMapper.map(product, GetAllProductResponse.class))
                .collect(Collectors.toList());

        return getAllProductResponses;
    }

    @Override
    public GetProductResponse getById(int id) {
        Product product = productRepository.findById(id).orElseThrow();
        GetProductResponse getProductResponse =
                modelMapper.map(product, GetProductResponse.class);

        return getProductResponse;
    }

    @Override
    public void changeState(int carId, ProductState productState) {
        Product product = productRepository.findById(carId).orElseThrow();
        product.setProductState(productState);
        productRepository.save(product);
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
