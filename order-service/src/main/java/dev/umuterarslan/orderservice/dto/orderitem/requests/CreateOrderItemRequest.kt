package dev.umuterarslan.orderservice.dto.orderitem.requests

data class CreateOrderItemRequest(
    val productId: String,
    val quantity: Int,
    val price: Double,
)
