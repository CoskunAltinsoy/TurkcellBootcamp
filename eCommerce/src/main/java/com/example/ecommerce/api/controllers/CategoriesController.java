package com.example.ecommerce.api.controllers;

import com.example.ecommerce.business.abstracts.CategoryService;
import com.example.ecommerce.business.dto.request.create.CreateCategoryRequest;
import com.example.ecommerce.business.dto.request.update.UpdateCategoryRequest;
import com.example.ecommerce.business.dto.response.create.CreateCategoryResponse;
import com.example.ecommerce.business.dto.response.get.GetAllCategoriesResponse;
import com.example.ecommerce.business.dto.response.get.GetCategoryResponse;
import com.example.ecommerce.business.dto.response.update.UpdateCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryService service;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateCategoryResponse> add(@RequestBody CreateCategoryRequest request){
        return ResponseEntity.ok(service.add(request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        service.delete(id);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }
    @PutMapping ()
    public ResponseEntity<UpdateCategoryResponse> update(@RequestBody UpdateCategoryRequest request){
        return ResponseEntity.ok(service.update(request));
    }
    @GetMapping()
    public ResponseEntity<List<GetAllCategoriesResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<GetCategoryResponse> getById(@PathVariable("id") int id){;
        return ResponseEntity.ok(service.getById(id));
    }
}