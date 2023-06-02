package com.example.ecommerce.business.concretes;

import com.example.ecommerce.business.abstracts.InvoiceService;
import com.example.ecommerce.business.abstracts.ProductService;
import com.example.ecommerce.business.abstracts.SaleService;
import com.example.ecommerce.business.dto.request.create.CreateInvoiceRequest;
import com.example.ecommerce.business.dto.request.create.CreateSaleRequest;
import com.example.ecommerce.business.dto.request.update.UpdateSaleRequest;
import com.example.ecommerce.business.dto.response.create.CreateSaleResponse;
import com.example.ecommerce.business.dto.response.get.GetAllSalesResponse;
import com.example.ecommerce.business.dto.response.get.GetProductResponse;
import com.example.ecommerce.business.dto.response.get.GetSaleResponse;
import com.example.ecommerce.business.dto.response.update.UpdateSaleResponse;
import com.example.ecommerce.entities.concretes.Product;
import com.example.ecommerce.entities.concretes.Sale;
import com.example.ecommerce.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleManager implements SaleService {
    private final SaleRepository repository;
    private final ModelMapper mapper;
    private final ProductService productService;
    private final InvoiceService invoiceService;
    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        Sale sale = mapper.map(request, Sale.class);
        sale.setId(0);

        Set<Product> products = request.getProducts().stream()
                .map(product -> mapper.map(productService.getById(product.getId()), Product.class))
                .collect(Collectors.toSet());

        sale.setProducts(products);
        sale.setTotalPrice(getTotalPrice(sale.getProducts()));
        getTotalQuantity(sale.getProducts());
        repository.save(sale);

        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest(request,invoiceRequest);
        invoiceService.add(invoiceRequest);

        CreateSaleResponse response = mapper.map(sale, CreateSaleResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UpdateSaleResponse update(UpdateSaleRequest request) {
        Sale sale = mapper.map(request, Sale.class);

        Set<Product> products = request.getProducts().stream()
                .map(product -> mapper.map(productService.getById(product.getId()), Product.class))
                .collect(Collectors.toSet());
        sale.setProducts(products);
        sale.setTotalPrice(getTotalPrice(sale.getProducts()));
        getTotalQuantity(sale.getProducts());
        repository.save(sale);

        UpdateSaleResponse response = mapper.map(sale, UpdateSaleResponse.class);
        return response;
    }

    @Override
    public List<GetAllSalesResponse> getAll() {
        List<Sale> sales = repository.findAll();
        List<GetAllSalesResponse> responses = sales
                .stream()
                .map(sale -> mapper.map(sale, GetAllSalesResponse.class))
                .collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetSaleResponse getById(int id) {
        Sale sale = repository.findById(id).orElseThrow();
        GetSaleResponse response =
                mapper.map(sale, GetSaleResponse.class);

        return response;
    }
    private void getTotalQuantity(Set<Product> products) {
        products.stream()
                .map(product -> {
                    GetProductResponse retrievedProduct = productService.getById(product.getId());
                    retrievedProduct.setQuantity(retrievedProduct.getQuantity() - product.getQuantity());
                    return retrievedProduct;
                })
                .collect(Collectors.toSet());
    }
    private Double getTotalPrice(Set<Product> products) {
        double sum = 0;
        sum = products.stream()
                .mapToDouble(product -> product.getQuantity() * product.getUnitPrice())
                .sum();

        return sum;
    }
    private void createInvoiceRequest(
            CreateSaleRequest saleRequest,
            CreateInvoiceRequest invoiceRequest
    ) {
        invoiceRequest.setCardHolder(saleRequest.getPaymentRequest().getCardHolder());
    }

}
