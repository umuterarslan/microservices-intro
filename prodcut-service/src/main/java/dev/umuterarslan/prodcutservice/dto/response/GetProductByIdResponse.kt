package dev.umuterarslan.prodcutservice.dto.response

import java.util.*

data class GetProductByIdResponse(
        val id: UUID,
        val name: String,
        val description: String,
        val price: Double,
        val createdAt: Date,
        val updatedAt: Date
)
