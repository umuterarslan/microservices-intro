package dev.umuterarslan.productservice.dto.request

import java.util.*

data class UpdateProductRequest(
        val id: String,
        val name: String,
        val description: String,
        val price: Double,
)
