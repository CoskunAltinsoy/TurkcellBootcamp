package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.BrandService;
import com.kodlama.io.rentacar.entities.concretes.Brand;
import com.kodlama.io.rentacar.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;

    public BrandManager(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    @Override
    public List<Brand> getAll() {
        return brandRepository.getAll();
    }
}
