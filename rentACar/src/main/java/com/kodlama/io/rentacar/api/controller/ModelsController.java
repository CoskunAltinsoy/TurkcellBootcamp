package com.kodlama.io.rentacar.api.controller;

import com.kodlama.io.rentacar.business.abstracts.ModelService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService modelService;

    public ModelsController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping()
    public List<GetAllModelsResponse> findAll(){
        return modelService.getAll();
    }
    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable("id") int id){
        return modelService.getById(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest createModelRequest){
        return modelService.add(createModelRequest);
    }
    @PutMapping()
    public UpdateModelResponse update(@RequestBody UpdateModelRequest updateModelRequest){
        return modelService.update(updateModelRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        modelService.delete(id);
    }
}
