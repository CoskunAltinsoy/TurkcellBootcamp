package com.example.ecommerce.api;

import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.entities.concretes.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/add")
    public ResponseEntity<Product> add(@RequestBody Product product){
        productService.add(product);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        productService.delete(id);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }
    @PutMapping ("/update")
    public ResponseEntity<Product> update(@RequestBody Product product){
        productService.update(product);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id){;
        return ResponseEntity.ok(productService.getById(id));
    }
}
