package dev.umuterarslan.prodcutservice.dto.request

import java.util.*

data class CreateProductRequest(
        val id: UUID,
        val name: String,
        val description: String,
        val price: Double,
        val createdAt: Date,
        val updatedAt: Date
)
