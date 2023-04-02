package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.kodlama.io.rentacar.entities.Brand;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(int id);
    CreateBrandResponse add(CreateBrandRequest createBrandRequest);
    UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest);
    void delete(int id);

}
