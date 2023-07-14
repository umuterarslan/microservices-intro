package dev.umuterarslan.prodcutservice.repository;

import dev.umuterarslan.prodcutservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
}
