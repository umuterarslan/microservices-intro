package dev.umuterarslan.productservice.service;

import dev.umuterarslan.productservice.dto.request.CreateProductRequest;
import dev.umuterarslan.productservice.dto.request.UpdateProductRequest;
import dev.umuterarslan.productservice.dto.response.*;
import dev.umuterarslan.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductService(ProductRepository repository) {
    }

    public CreateProductResponse createProduct(CreateProductRequest request) {
        return null;
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
