package dev.umuterarslan.productservice.mapper;

import dev.umuterarslan.productservice.dto.request.CreateProductRequest;
import dev.umuterarslan.productservice.dto.request.UpdateProductRequest;
import dev.umuterarslan.productservice.dto.response.*;
import dev.umuterarslan.productservice.model.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring")
@Repository
public interface ProductServiceMapper {
    Product createProductRequestToProduct(CreateProductRequest request);

    CreateProductResponse productToCreateProductResponse(Product product);

    GetProductByIdResponse productToGetProductByIdResponse(Product product);

    Page<GetProductsPaginatedResponse> productToGetProductsPaginatedResponse(Page<Product> products);

    Product updateProductRequestToProduct(UpdateProductRequest request);

    UpdateProductResponse porductToUpdateProductResponse(Product product);

    DeleteProductByIdResponse productToDeleteProductByIdResponse(Product product);
}
