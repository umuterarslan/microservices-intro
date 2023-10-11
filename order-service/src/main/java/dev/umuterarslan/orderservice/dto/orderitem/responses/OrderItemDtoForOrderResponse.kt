package dev.umuterarslan.orderservice.dto.orderitem.responses

data class OrderItemDtoForOrderResponse(
    var id: String = "",
    var productId: String = "",
    var quantity: Int = 0,
    var price: Double = 0.0,
)
