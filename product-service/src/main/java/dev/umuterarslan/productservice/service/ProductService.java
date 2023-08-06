package dev.umuterarslan.productservice.service;

import dev.umuterarslan.productservice.dto.request.CreateProductRequest;
import dev.umuterarslan.productservice.dto.request.UpdateProductRequest;
import dev.umuterarslan.productservice.dto.response.*;
import dev.umuterarslan.productservice.exceptions.DataSaveException;
import dev.umuterarslan.productservice.mapper.ProductServiceMapper;
import dev.umuterarslan.productservice.model.Product;
import dev.umuterarslan.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductServiceMapper mapper;

    public ProductService(ProductRepository repository, ProductServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CreateProductResponse createProduct(CreateProductRequest request) {
        Product product = mapper.createProductRequestToProduct(request);

        try {
            repository.save(product);
        } catch (Exception e) {
            throw new DataSaveException("Error occurred while saving data.");
        }

        return mapper.productToCreateProductResponse(product);
    }

    public GetProductByIdResponse getProductById(String id) {
        return null;
    }

    public GetProductsPaginatedResponse getProductsPaginated() {
        return null;
    }

    public UpdateProductResponse updateProduct(UpdateProductRequest request) {
        return null;
    }

    public DeleteProductByIdResponse deleteProductById(String id) {
        return null;
    }
}
