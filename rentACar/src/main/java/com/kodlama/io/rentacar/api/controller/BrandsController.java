package com.kodlama.io.rentacar.api.controller;

import com.kodlama.io.rentacar.business.abstracts.BrandService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import com.kodlama.io.rentacar.entities.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/brands")
public class BrandsController {
    private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }
    @GetMapping()
    public List<GetAllBrandsResponse> findAll(){
        return brandService.getAll();
    }
    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable("id") int id){
        return brandService.getById(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest createBrandRequest){
        return brandService.add(createBrandRequest);
    }
    @PutMapping()
    public Brand update(@RequestBody Brand brand){
        return brandService.update(brand);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        brandService.delete(id);
    }
}
