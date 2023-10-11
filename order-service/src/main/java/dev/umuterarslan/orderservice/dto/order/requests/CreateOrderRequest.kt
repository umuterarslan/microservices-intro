package dev.umuterarslan.orderservice.dto.order.requests

import dev.umuterarslan.orderservice.dto.orderitem.requests.CreateOrderItemRequest

data class CreateOrderRequest(
    val orderNumber: String,
    val userId: String,
    val address: String,
    val items: List<CreateOrderItemRequest>
)
