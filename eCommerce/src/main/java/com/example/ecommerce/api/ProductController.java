package com.example.ecommerce.api;

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
public class ProductController {
    private final ProductService productService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateProductResponse> add(@RequestBody CreateProductRequest createProductRequest){
        return ResponseEntity.ok(productService.add(createProductRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        productService.delete(id);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }
    @PutMapping ()
    public ResponseEntity<UpdateProductResponse> update(@RequestBody UpdateProductRequest updateProductRequest){
        return ResponseEntity.ok(productService.update(updateProductRequest));
    }
    @GetMapping()
    public ResponseEntity<List<GetAllProductsResponse>> getAll
            (@RequestParam(defaultValue = "true") boolean includeAvailable){
        return ResponseEntity.ok(productService.getAll(includeAvailable));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<GetProductResponse> getById(@PathVariable("id") int id){;
        return ResponseEntity.ok(productService.getById(id));
    }
}
