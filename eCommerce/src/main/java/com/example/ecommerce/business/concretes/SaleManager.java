package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.business.abstracts.SaleService;
import com.example.ecommerce.business.dto.request.create.CreateSaleRequest;
import com.example.ecommerce.business.dto.request.update.UpdateSaleRequest;
import com.example.ecommerce.business.dto.response.create.CreateSaleResponse;
import com.example.ecommerce.business.dto.response.get.GetAllSalesResponse;
import com.example.ecommerce.business.dto.response.get.GetSaleResponse;
import com.example.ecommerce.business.dto.response.update.UpdateSaleResponse;
import com.example.ecommerce.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleManager implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductService productService;
    @Override
    public CreateSaleResponse add(CreateSaleRequest createSaleRequest) {
        productService.ge
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public UpdateSaleResponse update(UpdateSaleRequest updateSaleRequest) {
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
