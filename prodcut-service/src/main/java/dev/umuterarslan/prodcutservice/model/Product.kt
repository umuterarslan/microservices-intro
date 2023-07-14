package dev.umuterarslan.prodcutservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("products")
data class Product(
        @Id
        val id: UUID,
        val name: String,
        val description: String,
        val price: Double,
        val createdAt: Date = Date()
)