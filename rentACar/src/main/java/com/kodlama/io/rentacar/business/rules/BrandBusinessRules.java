package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository brandRepository;

    public void checkIfBrandExistByName(String name){
        if (brandRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("This brand is registered in the system");
        }
    }
    public void checkIfBrandExistsById(int id) {
        if (!brandRepository.existsById(id)) {
            throw new RuntimeException("This brand is not registered in the system");
        }
    }
}
