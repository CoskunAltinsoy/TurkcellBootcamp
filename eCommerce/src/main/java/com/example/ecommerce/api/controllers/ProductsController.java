package com.example.ecommerce.api.controllers;

import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.business.dto.request.create.CreateProductRequest;
import com.example.ecommerce.business.dto.request.update.UpdateProductRequest;
import com.example.ecommerce.business.dto.response.create.CreateProductResponse;
import com.example.ecommerce.business.dto.response.get.GetAllProductsResponse;
import com.example.ecommerce.business.dto.response.get.GetProductResponse;
import com.example.ecommerce.business.dto.response.update.UpdateProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateProductResponse> add(@RequestBody CreateProductRequest createProductRequest){
        return ResponseEntity.ok(service.add(createProductRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        service.delete(id);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }
    @PutMapping ()
    public ResponseEntity<UpdateProductResponse> update(@RequestBody UpdateProductRequest updateProductRequest){
        return ResponseEntity.ok(service.update(updateProductRequest));
    }
    @GetMapping()
    public ResponseEntity<List<GetAllProductsResponse>> getAll
            (@RequestParam(defaultValue = "true") boolean includeAvailable){
        return ResponseEntity.ok(service.getAll(includeAvailable));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<GetProductResponse> getById(@PathVariable("id") int id){;
        return ResponseEntity.ok(service.getById(id));
    }

}
