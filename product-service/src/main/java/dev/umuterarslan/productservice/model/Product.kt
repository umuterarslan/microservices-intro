package dev.umuterarslan.productservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("products")
data class Product(
        @Id
        val id: UUID = UUID.randomUUID(),
        val name: String,
        val description: String,
        val price: Double,
        val createdAt: Date = Date(),
        val updatedAt: Date = Date()
)