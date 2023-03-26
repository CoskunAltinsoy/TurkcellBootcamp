package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.ModelService;
import com.kodlama.io.rentacar.entities.Model;
import com.kodlama.io.rentacar.repository.ModelRepository;
import org.springframework.stereotype.Service;

@Service
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;

    public ModelManager(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Model add(Model model) {
        return null;
    }
}
