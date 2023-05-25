package com.example.ecommerce.api.controllers;

import com.example.ecommerce.business.abstracts.SaleService;
import com.example.ecommerce.business.dto.request.create.CreateProductRequest;
import com.example.ecommerce.business.dto.request.create.CreateSaleRequest;
import com.example.ecommerce.business.dto.request.update.UpdateProductRequest;
import com.example.ecommerce.business.dto.request.update.UpdateSaleRequest;
import com.example.ecommerce.business.dto.response.create.CreateProductResponse;
import com.example.ecommerce.business.dto.response.create.CreateSaleResponse;
import com.example.ecommerce.business.dto.response.get.GetAllProductsResponse;
import com.example.ecommerce.business.dto.response.get.GetAllSalesResponse;
import com.example.ecommerce.business.dto.response.get.GetProductResponse;
import com.example.ecommerce.business.dto.response.get.GetSaleResponse;
import com.example.ecommerce.business.dto.response.update.UpdateProductResponse;
import com.example.ecommerce.business.dto.response.update.UpdateSaleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sales")
public class SalesController {
    private final SaleService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateSaleResponse> add(@RequestBody CreateSaleRequest request){
        return ResponseEntity.ok(service.add(request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        service.delete(id);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }
    @PutMapping ()
    public ResponseEntity<UpdateSaleResponse> update(@RequestBody UpdateSaleRequest request){
        return ResponseEntity.ok(service.update(request));
    }
    @GetMapping()
    public ResponseEntity<List<GetAllSalesResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<GetSaleResponse> getById(@PathVariable("id") int id){;
        return ResponseEntity.ok(service.getById(id));
    }
}
