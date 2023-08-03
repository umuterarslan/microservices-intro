package dev.umuterarslan.productservice.dto.response

import java.util.*

data class UpdateProductResponse(
        val id: UUID,
        val name: String,
        val description: String,
        val price: Double,
        val createdAt: Date,
        val updatedAt: Date
)
