package dev.umuterarslan.productservice.service.rules

import dev.umuterarslan.productservice.exceptions.ProductNotFoundException
import dev.umuterarslan.productservice.model.Product
import dev.umuterarslan.productservice.repository.ProductRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import java.util.*
import kotlin.test.assertEquals

class ProductServiceRulesTest {
    private lateinit var repository: ProductRepository
    private lateinit var underTest: ProductServiceRules

    @BeforeEach
    fun setUp() {
        repository = mock(ProductRepository::class.java)
        underTest = ProductServiceRules(repository)
    }

    @AfterEach
    fun tearDown() {
        reset(repository)
    }

    @Test
    fun `test checkIfProductDoesntExist when product doesn't exist`() {
        val productId = UUID.randomUUID()

        `when`(repository.findById(any())).thenReturn(Optional.empty())

        val exception = assertThrows<ProductNotFoundException> {
            underTest.checkIfProductDoesntExist(productId.toString())
        }

        assertEquals("Product not found.", exception.message)
        verify(repository).findById(productId)
    }

    @Test
    fun `test checkIfProductDoesntExist when product exists`() {
        val productId = UUID.randomUUID()

        `when`(repository.findById(any())).thenReturn(Optional.of(mock(Product::class.java)))

        underTest.checkIfProductDoesntExist(productId.toString())

        verify(repository).findById(productId)
    }
}