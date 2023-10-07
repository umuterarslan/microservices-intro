package dev.umuterarslan.productservice.mapper;

import dev.umuterarslan.productservice.dto.request.CreateProductRequest;
import dev.umuterarslan.productservice.dto.request.UpdateProductRequest;
import dev.umuterarslan.productservice.dto.response.*;
import dev.umuterarslan.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
@Repository
public interface ProductServiceMapper {
    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    Product createProductRequestToProduct(CreateProductRequest request);

    CreateProductResponse productToCreateProductResponse(Product product);

    GetProductByIdResponse productToGetProductByIdResponse(Product product);

    List<GetProductsPaginatedResponse> productToGetProductsPaginatedResponse(List<Product> products);

    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(request.getId()))")
    @Mapping(target = "createdAt", ignore = true)
    Product updateProductRequestToProduct(UpdateProductRequest request, Date createdAt);

    UpdateProductResponse porductToUpdateProductResponse(Product product);

    DeleteProductByIdResponse productToDeleteProductByIdResponse(Product product);
}
