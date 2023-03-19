package com.kodlama.io.rentacar.repository.concretes;

import com.kodlama.io.rentacar.entities.concretes.Brand;
import com.kodlama.io.rentacar.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class InMemoryBrandRepository implements BrandRepository {
    private List<Brand> brands;

    public InMemoryBrandRepository(List<Brand> brands) {
        this.brands = brands;
        brands.add(new Brand(1,"BMW"));
        brands.add(new Brand(2,"Mercedes"));
    }

    @Override
    public List<Brand> getAll() {
        return null;
    }
}
