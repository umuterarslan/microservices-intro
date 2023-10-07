package dev.umuterarslan.productservice.service;

import dev.umuterarslan.productservice.dto.request.CreateProductRequest;
import dev.umuterarslan.productservice.dto.request.UpdateProductRequest;
import dev.umuterarslan.productservice.dto.response.CreateProductResponse;
import dev.umuterarslan.productservice.dto.response.GetProductByIdResponse;
import dev.umuterarslan.productservice.dto.response.GetProductsPaginatedResponse;
import dev.umuterarslan.productservice.dto.response.UpdateProductResponse;
import dev.umuterarslan.productservice.exceptions.DataSaveException;
import dev.umuterarslan.productservice.exceptions.ProductNotFoundException;
import dev.umuterarslan.productservice.mapper.ProductServiceMapper;
import dev.umuterarslan.productservice.model.Product;
import dev.umuterarslan.productservice.repository.ProductRepository;
import dev.umuterarslan.productservice.service.rules.ProductServiceRules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductServiceMapper mapper;
    private final ProductServiceRules rules;

    public ProductService(ProductRepository repository, ProductServiceMapper mapper, ProductServiceRules rules) {
        this.repository = repository;
        this.mapper = mapper;
        this.rules = rules;
    }

    public CreateProductResponse createProduct(CreateProductRequest request) {
        Product product = mapper.createProductRequestToProduct(request);
        Product savedProduct = saveProductOrElseThrowAnException(product);
        return mapper.productToCreateProductResponse(savedProduct);
    }

    public GetProductByIdResponse getProductById(String id) {
        Product product = findProductByIdOrElseThrowAnException(id);
        return mapper.productToGetProductByIdResponse(product);

    }

    public Page<GetProductsPaginatedResponse> getProductsPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<Product> products = repository.findAll();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());

        List<GetProductsPaginatedResponse> responses = mapper.productToGetProductsPaginatedResponse(products);

        return new PageImpl<>(responses.subList(start, end), pageable, products.size());
    }

    public UpdateProductResponse updateProduct(UpdateProductRequest request) {
        Product currentProduct = findProductByIdOrElseThrowAnException(request.getId());

        Product product = mapper.updateProductRequestToProduct(request, currentProduct.getCreatedAt());

        Product savedProduct = saveProductOrElseThrowAnException(product);
        return mapper.porductToUpdateProductResponse(savedProduct);
    }

    public void deleteProductById(String id) {
        // an exception is thrown if product does not exist
        rules.checkIfProductDoesntExist(id);

        repository.deleteById(UUID.fromString(id));
    }

    private Product findProductByIdOrElseThrowAnException(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found."
                ));
    }

    // will throw an exception if there is an error while saving
    private Product saveProductOrElseThrowAnException(Product product) {
        Product savedProduct;

        try {
            savedProduct = repository.save(product);
        } catch (Exception e) {
            throw new DataSaveException("Error occurred while saving data.");
        }

        return savedProduct;
    }
}
