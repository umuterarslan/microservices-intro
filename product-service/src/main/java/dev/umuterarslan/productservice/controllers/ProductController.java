package dev.umuterarslan.productservice.controllers;

import dev.umuterarslan.productservice.dto.request.CreateProductRequest;
import dev.umuterarslan.productservice.dto.request.UpdateProductRequest;
import dev.umuterarslan.productservice.dto.response.CreateProductResponse;
import dev.umuterarslan.productservice.dto.response.GetProductByIdResponse;
import dev.umuterarslan.productservice.dto.response.GetProductsPaginatedResponse;
import dev.umuterarslan.productservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest request) {
        return service.createProduct(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductByIdResponse getProductById(@PathVariable String id) {
        return service.getProductById(id);
    }

    @GetMapping("/{pageNo}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public Page<GetProductsPaginatedResponse> getProductsPaginated(@PathVariable int pageNo, @PathVariable int pageSize) {
        return service.getProductsPaginated(pageNo, pageSize);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody UpdateProductRequest request) {
        service.updateProduct(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable String id) {
        service.deleteProductById(id);
    }
}
