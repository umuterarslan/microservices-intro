package dev.umuterarslan.productservice.service;

import dev.umuterarslan.productservice.dto.request.CreateProductRequest;
import dev.umuterarslan.productservice.dto.request.UpdateProductRequest;
import dev.umuterarslan.productservice.dto.response.*;
import dev.umuterarslan.productservice.exceptions.DataSaveException;
import dev.umuterarslan.productservice.exceptions.ProductNotFoundException;
import dev.umuterarslan.productservice.mapper.ProductServiceMapper;
import dev.umuterarslan.productservice.model.Product;
import dev.umuterarslan.productservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        Product product = findProductByIdOrElseThrowAnException(id);
        return mapper.productToGetProductByIdResponse(product);

    }

    public Page<GetProductsPaginatedResponse> getProductsPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> products = repository.findAll(pageable);
        return mapper.productToGetProductsPaginatedResponse(products);
    }

    public UpdateProductResponse updateProduct(UpdateProductRequest request) {
        return null;
    }

    public DeleteProductByIdResponse deleteProductById(String id) {
        return null;
    }

    private Product findProductByIdOrElseThrowAnException(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found."
                ));
    }
}
