package dev.umuterarslan.productservice.service.rules;

import dev.umuterarslan.productservice.exceptions.ProductNotFoundException;
import dev.umuterarslan.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceRules {
    private final ProductRepository repository;

    public ProductServiceRules(ProductRepository repository) {
        this.repository = repository;
    }

    public void checkIfProductDoesntExist(String id) {
        repository.findById(UUID.fromString(id))
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found.")
                );
    }
}
