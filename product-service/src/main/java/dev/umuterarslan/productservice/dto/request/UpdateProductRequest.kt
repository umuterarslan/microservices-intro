package dev.umuterarslan.productservice.dto.request

data class UpdateProductRequest(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int,
)
