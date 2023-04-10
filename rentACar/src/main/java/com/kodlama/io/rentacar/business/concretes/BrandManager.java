package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.BrandService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import com.kodlama.io.rentacar.config.ModelMapperService;
import com.kodlama.io.rentacar.entities.Brand;
import com.kodlama.io.rentacar.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandManager(
            BrandRepository brandRepository,
            ModelMapper modelMapper
    ) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }
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
        checkIfBrandExistsById(id);
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse = modelMapper.map(brand, GetBrandResponse.class);

        return getBrandResponse;
    }
    @Override
    public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
        checkIfBrandExistByName(createBrandRequest.getName());
        Brand brand = modelMapper.map(createBrandRequest, Brand.class);
        brand.setId(0);
        brandRepository.save(brand);

        CreateBrandResponse createBrandResponse = modelMapper.map(brand,CreateBrandResponse.class);
        return createBrandResponse;
    }

    @Override
    public UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest) {
        checkIfBrandExistsById(updateBrandRequest.getId());
        Brand brand = modelMapper.map(updateBrandRequest, Brand.class);
        brandRepository.save(brand);

        UpdateBrandResponse updateBrandResponse = modelMapper.map(brand,UpdateBrandResponse.class);

        return updateBrandResponse;
    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);
    }

    private void checkIfBrandExistByName(String name){
        if (brandRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("This brand is registered in the system");
        }
    }
    private void checkIfBrandExistsById(int id) {
        if (!brandRepository.existsById(id)) {
            throw new RuntimeException("This brand is not registered in the system");
        }
    }
}
