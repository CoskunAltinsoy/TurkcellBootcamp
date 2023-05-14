package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.CategoryService;
import com.example.ecommerce.business.dto.request.create.CreateCategoryRequest;
import com.example.ecommerce.business.dto.request.update.UpdateCategoryRequest;
import com.example.ecommerce.business.dto.response.create.CreateCategoryResponse;
import com.example.ecommerce.business.dto.response.get.GetAllCategoryResponse;
import com.example.ecommerce.business.dto.response.get.GetCategoryResponse;
import com.example.ecommerce.business.dto.response.update.UpdateCategoryResponse;
import com.example.ecommerce.business.dto.response.update.UpdateCategoryResponse;
import com.example.ecommerce.entities.concretes.Category;
import com.example.ecommerce.entities.concretes.Category;
import com.example.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest createCategoryRequest) {
        Category category = modelMapper.map(createCategoryRequest, Category.class);
        category.setId(0);
        categoryRepository.save(category);

        CreateCategoryResponse createCategoryResponse =
                modelMapper.map(category, CreateCategoryResponse.class);

        return createCategoryResponse;
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public UpdateCategoryResponse update(UpdateCategoryRequest updateCategoryRequest) {
        Category category = modelMapper.map(updateCategoryRequest, Category.class);
        categoryRepository.save(category);

        UpdateCategoryResponse updateCategoryResponse =
                modelMapper.map(category, UpdateCategoryResponse.class);

        return updateCategoryResponse;
    }

    @Override
    public List<GetAllCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<GetAllCategoryResponse> getAllCategoryResponses = categories
                .stream()
                .map(category -> modelMapper.map(category, GetAllCategoryResponse.class))
                .collect(Collectors.toList());

        return getAllCategoryResponses;
    }

    @Override
    public GetCategoryResponse getById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        GetCategoryResponse getCategoryResponse = modelMapper.map(category, GetCategoryResponse.class);

        return getCategoryResponse;
    }
}
