package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.entities.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();
    Brand getById(int id);
    Brand add(Brand brand);
    Brand update(Brand brand);
    void delete(int id);

}
