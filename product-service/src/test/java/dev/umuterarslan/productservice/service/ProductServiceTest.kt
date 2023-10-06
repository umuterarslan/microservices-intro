package dev.umuterarslan.productservice.service

import dev.umuterarslan.productservice.dto.request.CreateProductRequest
import dev.umuterarslan.productservice.dto.request.UpdateProductRequest
import dev.umuterarslan.productservice.dto.response.CreateProductResponse
import dev.umuterarslan.productservice.dto.response.GetProductByIdResponse
import dev.umuterarslan.productservice.dto.response.GetProductsPaginatedResponse
import dev.umuterarslan.productservice.dto.response.UpdateProductResponse
import dev.umuterarslan.productservice.exceptions.DataSaveException
import dev.umuterarslan.productservice.exceptions.ProductNotFoundException
import dev.umuterarslan.productservice.mapper.ProductServiceMapper
import dev.umuterarslan.productservice.model.Product
import dev.umuterarslan.productservice.repository.ProductRepository
import dev.umuterarslan.productservice.service.rules.ProductServiceRules
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.test.assertNotEquals

class ProductServiceTest {
    @Mock
    private lateinit var repository: ProductRepository
    @Mock
    private lateinit var rules: ProductServiceRules
    @Mock
    private lateinit var mapper: ProductServiceMapper
    private lateinit var underTest: ProductService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        underTest = ProductService(repository, mapper, rules)
    }

    @AfterEach
    fun tearDown() {
        Mockito.reset(repository)
    }

    @Test
    fun `test createProduct should return CreateProductResponse when product saved`() {
        val request = CreateProductRequest(
                "Test Product",
                "Test Description",
                100.0
        )

        val product = Product(
                UUID.randomUUID(),
                request.name,
                request.description,
                request.price,
                Date(),
                Date()
        )

        val savedProduct = Product(
                product.id,
                product.name,
                product.description,
                product.price,
                product.createdAt,
                product.updatedAt
        )

        val response = CreateProductResponse(
                savedProduct.id,
                savedProduct.name,
                savedProduct.description,
                savedProduct.price,
                savedProduct.createdAt,
                savedProduct.updatedAt
        )

        `when`(mapper.createProductRequestToProduct(request)).thenReturn(product)
        `when`(repository.save(product)).thenReturn(savedProduct)
        `when`(mapper.productToCreateProductResponse(savedProduct)).thenReturn(response)

        val createProductResponse = underTest.createProduct(request)

        assertEquals(response, createProductResponse)
    }

    @Test
    fun `test createProduct should throw DataSaveException when product not saved`() {
        val request = CreateProductRequest(
                "Test Product",
                "Test Description",
                100.0
        )

        val product = Product(
                UUID.randomUUID(),
                request.name,
                request.description,
                request.price,
                Date(),
                Date()
        )

        val exceptionMessage = "Error occurred while saving data."

        `when`(mapper.createProductRequestToProduct(request)).thenReturn(product)
        `when`(repository.save(product)).thenThrow(DataSaveException(exceptionMessage))

        val exception = assertThrows<DataSaveException> {
            underTest.createProduct(request)
        }

        assertEquals(exceptionMessage, exception.message)
    }

    @Test
    fun `test getProductById should return GetProductByIdResponse when product exists`() {
        val id = UUID.randomUUID()
        val name = "Test Product"
        val description = "Test Description"
        val price = 100.0
        val createdAt = Date()
        val updatedAt = Date()

        val product = Product(
                id,
                name,
                description,
                price,
                createdAt,
                updatedAt,
        )

        val response = GetProductByIdResponse(
                id,
                name,
                description,
                price,
                createdAt,
                updatedAt
        )

        `when`(repository.findById(id)).thenReturn(Optional.of(product))
        `when`(mapper.productToGetProductByIdResponse(product)).thenReturn(response)

        val getProductByIdResponse = underTest.getProductById(id.toString())

        assertEquals(getProductByIdResponse, response)
    }

    @Test
    fun `getProductById should throw ProductNotFoundException when product is does not exist`() {
        val id = UUID.randomUUID()
        val exceptionMessage = "Product not found."

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        val exception = assertThrows<ProductNotFoundException> {
            underTest.getProductById(id.toString())
        }

        assertEquals(exception.message, exceptionMessage)
    }

    @Test
    fun `test getProductsPaginated should return Page of GetProductsPaginatedResponse`() {
        val pageNo = 0
        val pageSize = 10
        val pageable = PageRequest.of(pageNo, pageSize)

        val paginatedProducts: Page<Product> = PageImpl(listOf(
                Product(
                        UUID.randomUUID(),
                        "Test Product 1",
                        "Test Description 1",
                        100.0,
                        Date(),
                        Date()
                )
        ), pageable, 1)

        val paginatedResponseProducts: Page<GetProductsPaginatedResponse> = PageImpl(listOf(
                GetProductsPaginatedResponse(
                        paginatedProducts.content[0].id,
                        paginatedProducts.content[0].name,
                        paginatedProducts.content[0].description,
                        paginatedProducts.content[0].price,
                        paginatedProducts.content[0].createdAt,
                        paginatedProducts.content[0].updatedAt
                )
        ), pageable, 1)

        `when`(repository.findAll(pageable)).thenReturn(paginatedProducts)
        `when`(mapper.productToGetProductsPaginatedResponse(paginatedProducts)).thenReturn(paginatedResponseProducts)

        val productsPaginated = underTest.getProductsPaginated(pageNo, pageSize)

        assertEquals(productsPaginated, paginatedResponseProducts)
    }

    @Test
    fun `test updateProduct should return UpdateProductResponse when product updated`() {
        val id = UUID.randomUUID()
        val request = UpdateProductRequest(
                id,
                "Test Product",
                "Test Description",
                100.0
        )

        val product = Product(
                id,
                request.name,
                request.description,
                request.price,
                Date(),
                Date()
        )

        val updatedProduct = Product(
                id,
                request.name,
                request.description,
                request.price,
                product.createdAt,
                Date()
        )

        val response = UpdateProductResponse(
                updatedProduct.id,
                updatedProduct.name,
                updatedProduct.description,
                updatedProduct.price,
                updatedProduct.createdAt,
                updatedProduct.updatedAt
        )

        `when`(mapper.updateProductRequestToProduct(request)).thenReturn(product)
        `when`(repository.save(product)).thenReturn(updatedProduct)
        `when`(mapper.porductToUpdateProductResponse(updatedProduct)).thenReturn(response)

        val updateProductResponse = underTest.updateProduct(request)

        assertEquals(response, updateProductResponse)
    }

    @Test
    fun `test updateProduct should throw DataSaveException when product not updated`() {
        val id = UUID.randomUUID()
        val exceptionMessage = "Error occurred while saving data."

        val request = UpdateProductRequest(
                id,
                "Test Product",
                "Test Description",
                100.0
        )

        val product = Product(
                id,
                request.name,
                request.description,
                request.price,
                Date(),
                Date()
        )

        `when`(mapper.updateProductRequestToProduct(request)).thenReturn(product)
        `when`(repository.save(product)).thenThrow(DataSaveException(exceptionMessage))

        val exception = assertThrows<DataSaveException> {
            underTest.updateProduct(request)
        }

        assertEquals(exceptionMessage, exception.message)
    }

    @Test
    fun `test deleteProduct should return nothing when product deleted`() {
        val id = UUID.randomUUID()

        doNothing().`when`(repository).deleteById(id)

        underTest.deleteProductById(id.toString())

        verify(repository).deleteById(id)
    }
}