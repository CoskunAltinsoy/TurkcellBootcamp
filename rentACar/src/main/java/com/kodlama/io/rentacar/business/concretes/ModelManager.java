package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.ModelService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import com.kodlama.io.rentacar.entities.Model;
import com.kodlama.io.rentacar.repository.ModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public ModelManager(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelsResponse> getAllModelsResponses = models
                .stream()
                .map(model -> modelMapper.map(model, GetAllModelsResponse.class))
                .collect(Collectors.toList());

        return getAllModelsResponses;
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExistsById(id);
        Model model = modelRepository.findById(id).orElseThrow();
        GetModelResponse getModelResponse = modelMapper.map(model, GetModelResponse.class);

        return getModelResponse;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest createModelRequest) {
        checkIfModelExistByName(createModelRequest.getName());
        Model model = modelMapper.map(createModelRequest, Model.class);
        model.setId(0);
        modelRepository.save(model);

        CreateModelResponse createModelResponse = modelMapper.map(model, CreateModelResponse.class);
        return createModelResponse;
    }

    @Override
    public UpdateModelResponse update(UpdateModelRequest updateModelRequest) {
        checkIfModelExistsById(updateModelRequest.getId());
        Model model = modelMapper.map(updateModelRequest, Model.class);
        modelRepository.save(model);

        UpdateModelResponse updateModelResponse = modelMapper.map(model, UpdateModelResponse.class);
        return updateModelResponse;
    }

    @Override
    public void delete(int id) {
        modelRepository.deleteById(id);
    }

    private void checkIfModelExistByName(String name){
        if (modelRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("This model is registered in the system");
        }
    }
    private void checkIfModelExistsById(int id) {
        if (!modelRepository.existsById(id)) {
            throw new RuntimeException("This model is not registered in the system");
        }
    }
}
