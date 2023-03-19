package com.kodlama.io.rentacar.api.controller;

import com.kodlama.io.rentacar.business.abstracts.BrandService;
import com.kodlama.io.rentacar.entities.concretes.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/brands")
public class BrandsController {
    private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }
    @GetMapping("/getall")
    public List<Brand> findAll(){
        return brandService.getAll();
    }
}
