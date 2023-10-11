package dev.umuterarslan.orderservice.dto.order.responses

import dev.umuterarslan.orderservice.dto.orderitem.responses.OrderItemDtoForOrderResponse
import java.util.*

data class CreateOrderResponse(
    val id: String,
    val orderNumber: String,
    val userId: String,
    val address: String,
    val createdDate: Date,
    val items: List<OrderItemDtoForOrderResponse>
)
