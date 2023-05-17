package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository modelRepository;

    public void checkIfModelExistByName(String name){
        if (modelRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("This model is registered in the system");
        }
    }
    public void checkIfModelExistsById(int id) {
        if (!modelRepository.existsById(id)) {
            throw new RuntimeException("This model is not registered in the system");
        }
    }
}
