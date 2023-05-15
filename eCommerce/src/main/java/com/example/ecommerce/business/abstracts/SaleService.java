package com.example.ecommerce.business.abstracts;

import com.example.ecommerce.business.dto.request.create.CreateSaleRequest;
import com.example.ecommerce.business.dto.request.update.UpdateSaleRequest;
import com.example.ecommerce.business.dto.response.create.CreateSaleResponse;
import com.example.ecommerce.business.dto.response.get.GetAllSalesResponse;
import com.example.ecommerce.business.dto.response.get.GetSaleResponse;
import com.example.ecommerce.business.dto.response.update.UpdateSaleResponse;

import java.util.List;

public interface SaleService {
    CreateSaleResponse add(CreateSaleRequest createSaleRequest);
    void delete(int id);
    UpdateSaleResponse update(UpdateSaleRequest updateSaleRequest);
    List<GetAllSalesResponse> getAll();
    GetSaleResponse getById(int id);
}
