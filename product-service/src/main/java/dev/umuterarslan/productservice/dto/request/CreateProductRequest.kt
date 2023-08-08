package dev.umuterarslan.productservice.dto.request

import java.util.*

data class CreateProductRequest(
        val name: String,
        val description: String,
        val price: Double,
)
