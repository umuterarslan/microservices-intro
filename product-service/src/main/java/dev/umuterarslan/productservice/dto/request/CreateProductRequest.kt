package dev.umuterarslan.productservice.dto.request

data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int,
)
