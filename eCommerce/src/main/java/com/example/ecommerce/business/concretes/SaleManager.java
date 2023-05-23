package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.business.abstracts.SaleService;
import com.example.ecommerce.business.dto.request.ProductRequest;
import com.example.ecommerce.business.dto.request.create.CreateSaleRequest;
import com.example.ecommerce.business.dto.request.update.UpdateSaleRequest;
import com.example.ecommerce.business.dto.response.create.CreateSaleResponse;
import com.example.ecommerce.business.dto.response.get.GetAllProductsResponse;
import com.example.ecommerce.business.dto.response.get.GetAllSalesResponse;
import com.example.ecommerce.business.dto.response.get.GetProductResponse;
import com.example.ecommerce.business.dto.response.get.GetSaleResponse;
import com.example.ecommerce.business.dto.response.update.UpdateSaleResponse;
import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.entities.concretes.Sale;
import com.example.ecommerce.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleManager implements SaleService {
    private final SaleRepository repository;
    private final ModelMapper mapper;
    private final ProductService productService;
    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        Sale sale = mapper.map(request, Sale.class);
        sale.setId(0);

        request.getProducts().stream()
                        .forEach(product -> sale.getProducts()
                                .add(mapper.map(productService.getById(product.getId()), Product.class)));

        repository.save(sale);

        CreateSaleResponse response = mapper.map(sale, CreateSaleResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public UpdateSaleResponse update(UpdateSaleRequest request) {
        return null;
    }

    @Override
    public List<GetAllSalesResponse> getAll() {
        return null;
    }

    @Override
    public GetSaleResponse getById(int id) {
        return null;
    }

}
