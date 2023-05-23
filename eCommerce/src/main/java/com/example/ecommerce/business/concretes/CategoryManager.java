package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.CategoryService;
import com.example.ecommerce.business.dto.request.create.CreateCategoryRequest;
import com.example.ecommerce.business.dto.request.update.UpdateCategoryRequest;
import com.example.ecommerce.business.dto.response.create.CreateCategoryResponse;
import com.example.ecommerce.business.dto.response.get.GetAllCategoriesResponse;
import com.example.ecommerce.business.dto.response.get.GetCategoryResponse;
import com.example.ecommerce.business.dto.response.update.UpdateCategoryResponse;
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

    private final CategoryRepository repository;
    private final ModelMapper mapper;

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        Category category = mapper.map(request, Category.class);
        category.setId(0);
        repository.save(category);

        CreateCategoryResponse response =
                mapper.map(category, CreateCategoryResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UpdateCategoryResponse update(UpdateCategoryRequest request) {
        Category category = mapper.map(request, Category.class);
        repository.save(category);

        UpdateCategoryResponse response =
                mapper.map(category, UpdateCategoryResponse.class);

        return response;
    }

    @Override
    public List<GetAllCategoriesResponse> getAll() {
        List<Category> categories = repository.findAll();
        List<GetAllCategoriesResponse> responses = categories
                .stream()
                .map(category -> mapper.map(category, GetAllCategoriesResponse.class))
                .collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetCategoryResponse getById(int id) {
        Category category = repository.findById(id).orElseThrow();
        GetCategoryResponse response = mapper.map(category, GetCategoryResponse.class);

        return response;
    }
}
