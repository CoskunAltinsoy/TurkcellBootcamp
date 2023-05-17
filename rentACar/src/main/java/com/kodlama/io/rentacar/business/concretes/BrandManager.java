package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.BrandService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.kodlama.io.rentacar.business.rules.BrandBusinessRules;
import com.kodlama.io.rentacar.entities.Brand;
import com.kodlama.io.rentacar.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandsResponse> getAllBrandsResponses =  brands.stream()
                .map(brand -> modelMapper.map(brand, GetAllBrandsResponse.class))
                .collect(Collectors.toList());
        return getAllBrandsResponses;
    }
    @Override
    public GetBrandResponse getById(int id) {
        brandBusinessRules.checkIfBrandExistsById(id);
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse = modelMapper.map(brand, GetBrandResponse.class);

        return getBrandResponse;
    }
    @Override
    public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
        brandBusinessRules.checkIfBrandExistByName(createBrandRequest.getName());
        Brand brand = modelMapper.map(createBrandRequest, Brand.class);
        brand.setId(0);
        brandRepository.save(brand);

        CreateBrandResponse createBrandResponse = modelMapper.map(brand,CreateBrandResponse.class);
        return createBrandResponse;
    }

    @Override
    public UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest) {
        brandBusinessRules.checkIfBrandExistsById(updateBrandRequest.getId());
        Brand brand = modelMapper.map(updateBrandRequest, Brand.class);
        brandRepository.save(brand);

        UpdateBrandResponse updateBrandResponse = modelMapper.map(brand,UpdateBrandResponse.class);

        return updateBrandResponse;
    }

    @Override
    public void delete(int id) {
        brandBusinessRules.checkIfBrandExistsById(id);
        brandRepository.deleteById(id);
    }


}
