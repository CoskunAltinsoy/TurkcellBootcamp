package com.example.ecommerce.business.abstracts;

import com.example.ecommerce.business.dto.request.create.CreateCategoryRequest;
import com.example.ecommerce.business.dto.request.update.UpdateCategoryRequest;
import com.example.ecommerce.business.dto.response.create.CreateCategoryResponse;
import com.example.ecommerce.business.dto.response.get.GetAllCategoriesResponse;
import com.example.ecommerce.business.dto.response.get.GetCategoryResponse;
import com.example.ecommerce.business.dto.response.update.UpdateCategoryResponse;

import java.util.List;

public interface CategoryService {
    CreateCategoryResponse add(CreateCategoryRequest request);
    void delete(int id);
    UpdateCategoryResponse update(UpdateCategoryRequest request);
    List<GetAllCategoriesResponse> getAll();
    GetCategoryResponse getById(int id);
}
