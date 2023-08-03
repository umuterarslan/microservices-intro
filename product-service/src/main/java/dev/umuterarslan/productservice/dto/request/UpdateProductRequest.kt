package dev.umuterarslan.productservice.dto.request

import java.util.*

data class UpdateProductRequest(
        val id: UUID,
        val name: String,
        val description: String,
        val price: Double,
        val createdAt: Date,
        val updatedAt: Date
)
