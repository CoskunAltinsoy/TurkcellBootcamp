package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.BrandService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import com.kodlama.io.rentacar.entities.Brand;
import com.kodlama.io.rentacar.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;

    public BrandManager(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandsResponse> getAllBrandsResponses = new ArrayList<>();
        brands.stream()
                .forEach(brand -> getAllBrandsResponses.add(new GetAllBrandsResponse(brand.getId(), brand.getName())));
        return getAllBrandsResponses;
    }

    @Override
    public GetBrandResponse getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse =
                new GetBrandResponse(
                        brand.getId(),
                        brand.getName()
                );
        return getBrandResponse;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
        Brand brand = new Brand();
        brand.setName(createBrandRequest.getName());
        brandRepository.save(brand);

        CreateBrandResponse createBrandResponse =
                                new CreateBrandResponse(
                                        brand.getId(),
                                        brand.getName()
                                );
        return createBrandResponse;
    }

    @Override
    public Brand update(Brand brand) {
       // Brand updatedBrand = getById(brand.getId());
       // updatedBrand.setName(brand.getName());
       // return brandRepository.save(updatedBrand);
        return null;
    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);
    }
}
